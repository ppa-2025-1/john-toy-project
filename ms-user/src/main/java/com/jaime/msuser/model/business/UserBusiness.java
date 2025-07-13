package com.jaime.msuser.model.business;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jaime.msuser.dto.ChamadoRequest;
import com.jaime.msuser.dto.NewUser;
import com.jaime.msuser.model.entity.Profile;
import com.jaime.msuser.model.entity.Role;
import com.jaime.msuser.model.entity.User;
import com.jaime.msuser.repository.RoleRepository;
import com.jaime.msuser.repository.UserRepository;
import com.jaime.msuser.service.ChamadoService;

@Business
public class UserBusiness {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final Set<String> defaultRoles;
    private final ChamadoService chamadoService;

    public UserBusiness(
        UserRepository userRepository,
        RoleRepository roleRepository,
        @Value("${app.user.default.roles}") Set<String> defaultRoles,
        ChamadoService chamadoService
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.defaultRoles = defaultRoles;
        this.chamadoService = chamadoService;
    }

    public void criarUsuario(NewUser newUser) {
        if (!newUser.email().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Email não é válido");
        }

        if (!newUser.password().matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$")) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres e conter pelo menos uma letra e um número");
        }

        userRepository.findByEmail(newUser.email()).ifPresent(user -> {
            throw new IllegalArgumentException("Usuário com o email " + newUser.email() + " já existe");
        });

        userRepository.findByHandle(newUser.handle()).ifPresent(user -> {
            throw new IllegalArgumentException("Usuário com o nome " + newUser.handle() + " já existe");
        });

        User user = new User();
        user.setEmail(newUser.email());
        user.setHandle(newUser.handle() != null ? newUser.handle() : generateHandle(newUser.email()));
        user.setPassword(new BCryptPasswordEncoder().encode(newUser.password()));

        Set<Role> roles = new HashSet<>();
        roles.addAll(roleRepository.findByNameIn(defaultRoles));

        Set<Role> additionalRoles = roleRepository.findByNameIn(newUser.roles());
        if (additionalRoles.size() != newUser.roles().size()) {
            throw new IllegalArgumentException("Alguns papéis não existem");
        }

        if (roles.isEmpty()) {
            throw new IllegalArgumentException("O usuário deve ter pelo menos um papel");
        }

        user.setRoles(roles);

        Profile profile = new Profile();
        profile.setName(newUser.name());
        profile.setCompany(newUser.company());
        profile.setType(newUser.type() != null ? newUser.type() : Profile.AccountType.FREE);
        profile.setUser(user);
        user.setProfile(profile);

        userRepository.save(user);

        // Criar chamado automático para o novo usuário
        ChamadoRequest chamado = new ChamadoRequest();
        chamado.setAcao("CRIAR");
        chamado.setObjeto("E-MAIL");
        chamado.setDetalhamento("Criar e-mail " + user.getEmail());
        chamado.setUsuarioHandle(user.getHandle());

        boolean chamadoCriado = chamadoService.criarChamadoAutomatico(chamado);
        
        if (!chamadoCriado) {
            // Log do erro mas não falha a criação do usuário
            System.err.println("Aviso: Não foi possível criar o chamado automático para o usuário: " + user.getHandle());
        }
    }

    private String generateHandle(String email) {
        String[] parts = email.split("@");
        String handle = parts[0];
        int i = 1;
        while (userRepository.existsByHandle(handle)) {
            handle = parts[0] + i++;
        }
        return handle;
    }
}


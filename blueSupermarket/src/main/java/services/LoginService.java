package services;

import DAO.UsuarioDao;
import exception.LoginInvalidoException;
import model.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginService {

    private final UsuarioDao usuarioDao = new UsuarioDao();

    public Usuario login(HttpServletRequest req, HttpServletResponse resp) throws LoginInvalidoException, IOException {
        String cpf = req.getParameter("cpf");
        String senha = req.getParameter("senha");
        String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
        System.out.println(gRecaptchaResponse);
//        Usuario usuario = usuarioDao.consultarUsuarioPorCpf(cpf);
//        System.out.println(usuario.getSenha());
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
//        if (usuario != null && usuario.getSenha().equals(senha)) {
//            return usuario;
//        } else {
//            throw new LoginInvalidoException();
//        }
        System.out.println(verify);
        return null;
    }
}

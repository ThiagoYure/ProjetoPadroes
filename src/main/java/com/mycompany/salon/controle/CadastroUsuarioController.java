package com.mycompany.salon.controle;

import com.mycompany.salon.modelo.Usuario;
import com.mycompany.salon.persistencia.UsuarioDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author alexalins
 */
public class CadastroUsuarioController extends SimpleTagSupport{
    
    Usuario usuario = new Usuario();
    
    @Override
    public void doTag() throws JspException {
        UsuarioDao usuarioDao = new UsuarioDao();
        try {
            getJspContext().setAttribute("UsuarioCadastro", usuarioDao.cadastroUsuario(usuario));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}

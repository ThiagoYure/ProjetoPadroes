package com.mycompany.salon.controle;

import com.mycompany.salon.modelo.Atendente;
import com.mycompany.salon.persistencia.AtendenteDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author alexalins
 */
public class CadastroAtendenteController extends SimpleTagSupport{
    
    Atendente atendente = new Atendente();
    
    @Override
    public void doTag() throws JspException {
        AtendenteDao atendenteDao = new AtendenteDao();
        try {
            getJspContext().setAttribute("AtendenteCadastro", atendenteDao.cadastroAtendente(atendente));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroAtendenteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }
    
    
}

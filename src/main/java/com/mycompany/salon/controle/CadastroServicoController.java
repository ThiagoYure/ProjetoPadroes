package com.mycompany.salon.controle;

import com.mycompany.salon.modelo.Servico;
import com.mycompany.salon.persistencia.ServicoDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author alexalins
 */
public class CadastroServicoController extends SimpleTagSupport {

    Servico servico = new Servico();

    @Override
    public void doTag() throws JspException {
        ServicoDao servicoDao = new ServicoDao();
        try {
            getJspContext().setAttribute("ServicoCadastro", servicoDao.cadastroServico(servico));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroServicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.fci.tc.lp2.servlet;

import br.mackenzie.fci.tc.lp2.dao.CidadeDAO;
import br.mackenzie.fci.tc.lp2.dao.EstadoDAO;
import br.mackenzie.fci.tc.lp2.exception.PersistenciaException;
import br.mackenzie.fci.tc.lp2.model.Cidade;
import br.mackenzie.fci.tc.lp2.model.Estado;
import br.mackenzie.fci.tc.lp2.model.Hotel;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 31319238
 */
@WebServlet(name = "EstadoController", urlPatterns = {"/estado"})
public class EstadoController extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("opcao") != null) {

            if ("estado.preInserir".equalsIgnoreCase(request.getParameter("opcao"))) {
                response.sendRedirect("./jsp/estado/inserir.jsp");
            }

            if ("estado.inserir".equalsIgnoreCase(request.getParameter("opcao"))) {

                try {
                    Estado estado = new Estado();
                    estado.setNome(request.getParameter("nome"));
                    new EstadoDAO().inserir(estado);
                    request.setAttribute("estados", new EstadoDAO().listar());
                    request.getRequestDispatcher("./jsp/estado/listar.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(EstadoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ("estado.listar".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    List<Estado> estados = new EstadoDAO().listar();
                    request.setAttribute("estados", estados);
                    request.getRequestDispatcher("./jsp/estado/listar.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(EstadoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if ("estado.prelistarf".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    List<Estado> estados = new EstadoDAO().listar();
                    request.setAttribute("estados", estados);
                    request.getRequestDispatcher("./jsp/estado/pre-listar-f.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(EstadoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if ("estado.listarf".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    List<Estado> estados = new EstadoDAO().listar();
                    request.setAttribute("estados", estados);
                    request.setAttribute("dEscolhido", new EstadoDAO().buscarPorId(Integer.parseInt(request.getParameter("estados"))));
                    List<Hotel> hoteis = new EstadoDAO().listarHoteis(Integer.parseInt(request.getParameter("estados")));
                    request.setAttribute("hoteis", hoteis);
                    request.getRequestDispatcher("./jsp/estado/listar-f.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(EstadoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if ("estado.preEditar".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    request.setAttribute("estados", new EstadoDAO().listar());
                    request.getRequestDispatcher("./jsp/estado/editar.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(EstadoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ("estado.alterarPorId".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    Estado estado = new EstadoDAO().buscarPorId(Integer.parseInt(request.getParameter("estados")));
                    request.setAttribute("estado", estado);
                    request.getRequestDispatcher("./jsp/estado/confirmar-edicao.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(EstadoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ("estado.alterar".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    Estado estado = new Estado();
                    estado.setCodigo(Integer.parseInt(request.getParameter("codigo")));
                    estado.setNome(request.getParameter("nome"));
                    new EstadoDAO().atualizar(estado);
                    request.setAttribute("estados", new EstadoDAO().listar());
                    request.getRequestDispatcher("./jsp/estado/listar.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(EstadoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ("estado.preExcluir".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    request.setAttribute("estados", new EstadoDAO().listar());
                    request.getRequestDispatcher("./jsp/estado/excluir.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(EstadoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ("estado.excluirPorId".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    Estado estado = new EstadoDAO().buscarPorId(Integer.parseInt(request.getParameter("estados")));
                    request.setAttribute("estado", estado);
                    request.getRequestDispatcher("./jsp/estado/confirmar-exclusao.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(EstadoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ("estado.excluir".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    Estado estado = new Estado(Integer.parseInt(request.getParameter("codigo")),
                            request.getParameter("nome"));
                    new EstadoDAO().excluir(estado.getCodigo());
                    request.setAttribute("estados", new EstadoDAO().listar());
                    request.getRequestDispatcher("./jsp/estado/listar.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(EstadoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

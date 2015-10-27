/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.fci.tc.lp2.servlet;


import br.mackenzie.fci.tc.lp2.dao.CidadeDAO;
import br.mackenzie.fci.tc.lp2.exception.PersistenciaException;
import br.mackenzie.fci.tc.lp2.model.Cidade;
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
@WebServlet(name = "cidade", urlPatterns = {"/cidade"})
public class CidadeController extends HttpServlet {

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

            if ("cidade.preInserir".equalsIgnoreCase(request.getParameter("opcao"))) {
                response.sendRedirect("./jsp/cidade/inserir.jsp");
            }

            if ("cidade.inserir".equalsIgnoreCase(request.getParameter("opcao"))) {

                try {
                    Cidade cidade = new Cidade();
                    cidade.setNome(request.getParameter("nome"));
                    new CidadeDAO().inserir(cidade);
                    request.setAttribute("cidades", new CidadeDAO().listar());
                    request.getRequestDispatcher("./jsp/cidade/listar.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if ("cidade.listar".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    List<Cidade> cidades = new CidadeDAO().listar();
                    request.setAttribute("cidades", cidades);
                    request.getRequestDispatcher("./jsp/cidade/listar.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if ("cidade.prelistarf".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    List<Cidade> cidades = new CidadeDAO().listar();
                    request.setAttribute("cidades", cidades);
                    request.getRequestDispatcher("./jsp/cidade/pre-listar-f.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if ("cidade.listarf".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    List<Cidade> cidades = new CidadeDAO().listar();
                    request.setAttribute("cidades", cidades);
                    request.setAttribute("cEscolhido", new CidadeDAO().buscarPorId(Integer.parseInt(request.getParameter("cidades"))));
                    List<Hotel> hoteis = new CidadeDAO().listarHoteis(Integer.parseInt(request.getParameter("cidades")));
                    request.setAttribute("hoteis", hoteis);
                    request.getRequestDispatcher("./jsp/cidade/listar-f.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if ("cidade.preEditar".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    request.setAttribute("cidades", new CidadeDAO().listar());
                    request.getRequestDispatcher("./jsp/cidade/editar.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ("cidade.alterarPorId".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    Cidade cidade = new CidadeDAO().buscarPorId(Integer.parseInt(request.getParameter("cidades")));
                    request.setAttribute("cidade", cidade);
                    request.getRequestDispatcher("./jsp/cidade/confirmar-alteracao.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ("cidade.alterar".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    Cidade cidade = new Cidade();
                    cidade.setCodigo(Integer.parseInt(request.getParameter("codigo")));
                    cidade.setNome(request.getParameter("nome"));
                    new CidadeDAO().atualizar(cidade);
                    request.setAttribute("cidades", new CidadeDAO().listar());
                    request.getRequestDispatcher("./jsp/cidade/listar.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ("cidade.preExcluir".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    request.setAttribute("cidades", new CidadeDAO().listar());
                    request.getRequestDispatcher("./jsp/cidade/excluir.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ("cidade.excluirPorId".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    Cidade cidade = new CidadeDAO().buscarPorId(Integer.parseInt(request.getParameter("cidades")));
                    request.setAttribute("cidade", cidade);
                    request.getRequestDispatcher("./jsp/cidade/confirmar-exclusao.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ("cidade.excluir".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    Cidade cidade = new Cidade(Integer.parseInt(request.getParameter("codigo")), 
                            request.getParameter("nome"));
                    new CidadeDAO().excluir(cidade.getCodigo());
                    request.setAttribute("cidades", new CidadeDAO().listar());
                    request.getRequestDispatcher("./jsp/cidade/listar.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, null, ex);
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

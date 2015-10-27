/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.fci.tc.lp2.servlet;


import br.mackenzie.fci.tc.lp2.dao.CidadeDAO;
import br.mackenzie.fci.tc.lp2.dao.EstadoDAO;
import br.mackenzie.fci.tc.lp2.dao.HotelDAO;
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
@WebServlet(name = "HotelController", urlPatterns = {"/hotel"})
public class HotelController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("opcao") != null) {
            if ("hotel.listar".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    List<Hotel> hoteis = new HotelDAO().listar();
                    request.setAttribute("hoteis", hoteis);
                    request.getRequestDispatcher("./jsp/hotel/listar.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(HotelController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if ("hotel.preInserir".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    List<Cidade> cidades = new CidadeDAO().listar();
                    request.setAttribute("cidades", cidades);
                    List<Estado> estados = new EstadoDAO().listar();
                    request.setAttribute("estados", estados);
                    request.getRequestDispatcher("./jsp/hotel/inserir.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(HotelController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if ("hotel.inserir".equalsIgnoreCase(request.getParameter("opcao"))) {

                try {
                    Hotel hotel = new Hotel();
                    hotel.setNome(request.getParameter("nome"));
                    hotel.setCidade(new CidadeDAO().buscarPorId(Integer.parseInt
                            (request.getParameter("cidades"))));
                    hotel.setEstado(new EstadoDAO().buscarPorId
                            (Integer.parseInt(request.getParameter("estados"))));
                    new HotelDAO().inserir(hotel);
                    request.setAttribute("hoteis", new HotelDAO().listar());
                    request.getRequestDispatcher("./jsp/hotel/listar.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(HotelController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if ("hotel.preEditar".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    request.setAttribute("hoteis", new HotelDAO().listar());
                    request.getRequestDispatcher("./jsp/hotel/editar.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(HotelController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ("hotel.editarPorId".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    Hotel hotel = new HotelDAO().buscarPorId(Integer.parseInt(request.getParameter("hoteis")));
                    request.setAttribute("cidades", new CidadeDAO().listar());
                    request.setAttribute("estados", new EstadoDAO().listar());
                    request.setAttribute("hotel", hotel);
                    request.getRequestDispatcher("./jsp/hotel/confirmar-edicao.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(HotelController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ("hotel.editar".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    Hotel hotel = new Hotel();
                    hotel.setCodigo(Integer.parseInt(request.getParameter("codigo")));
                    hotel.setNome(request.getParameter("nome"));
                    hotel.setCidade(new CidadeDAO().buscarPorId
                            (Integer.parseInt(request.getParameter("cidades"))));
                    hotel.setEstado(new EstadoDAO().buscarPorId
                            (Integer.parseInt(request.getParameter("estados"))));
                    new HotelDAO().atualizar(hotel);
                    request.setAttribute("hoteis", new HotelDAO().listar());
                    request.getRequestDispatcher("./jsp/hotel/listar.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(HotelController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ("hotel.preExcluir".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    request.setAttribute("hoteis", new HotelDAO().listar());
                    request.getRequestDispatcher("./jsp/hotel/excluir.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(HotelController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ("hotel.excluirPorId".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    Hotel hotel = new HotelDAO().buscarPorId(Integer.parseInt(request.getParameter("hoteis")));
                    request.setAttribute("hotel", hotel);
                    request.getRequestDispatcher("./jsp/hotel/confirmar-exclusao.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(HotelController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ("hotel.excluir".equalsIgnoreCase(request.getParameter("opcao"))) {
                try {
                    Hotel hotel = new Hotel();
                    hotel.setCodigo(Integer.parseInt(request.getParameter("codigo")));
                    hotel.setNome(request.getParameter("nome"));
                    new HotelDAO().excluir(hotel.getCodigo());
                    request.setAttribute("hoteis", new HotelDAO().listar());
                    request.getRequestDispatcher("./jsp/hotel/listar.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(HotelController.class.getName()).log(Level.SEVERE, null, ex);
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

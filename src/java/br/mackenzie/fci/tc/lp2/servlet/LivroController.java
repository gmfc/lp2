/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.fci.tc.lp2.servlet;

import br.mackenzie.fci.tc.lp2.dao.LivroDAO;
import br.mackenzie.fci.tc.lp2.dao.UsuarioDAO;
import br.mackenzie.fci.tc.lp2.dao.Usuario_avalia_LivroDAO;
import br.mackenzie.fci.tc.lp2.exception.PersistenciaException;
import br.mackenzie.fci.tc.lp2.model.Livro;
import br.mackenzie.fci.tc.lp2.model.Usuario;
import br.mackenzie.fci.tc.lp2.model.Usuario_avalia_Livro;
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
 * @author 31371477
 */
@WebServlet(name = "LivroController", urlPatterns = {"/livro"})
public class LivroController extends HttpServlet {

    private String command;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("opcao") != null) {
            if ("livro.listar".equalsIgnoreCase(request.getParameter("opcao"))) {
                System.out.println("ola!xdfhgsdfgsdfhgkjsdfhgksjdfg");
                try {
                    List<Livro> livros = new LivroDAO().listar();
                    request.setAttribute("livros", livros);
                    request.getRequestDispatcher("/jsp/listalivro.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(LivroController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (request.getParameter("opcao").contains("livro.get")) {
                System.out.println("GETTTTTTTJHGFDJGHFJGHFD");
                try {
                    String helper[] = request.getParameter("opcao").split("-");
                    int ID = Integer.parseInt(helper[1]);
                    Livro livro = new LivroDAO().buscarPorId(ID);
                    List<Usuario_avalia_Livro> ual = new Usuario_avalia_LivroDAO().listarPorIdLivro(ID);
                    request.setAttribute("livro", livro);
                    request.setAttribute("notas", ual);
                    double notamedia = 0;
                    for (Usuario_avalia_Livro ual1 : ual) {
                        notamedia += ual1.getNota();
                    }
                    notamedia = notamedia / ual.size();
                    request.setAttribute("notamedia", notamedia);
                    request.getRequestDispatcher("/jsp/livro.jsp").forward(request, response);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(LivroController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        } else if (command.endsWith("insert")) {
            try {
                String nome = request.getParameter("nome");
                int nota = Integer.parseInt(request.getParameter("nota"));
                int livroID = Integer.parseInt(request.getParameter("livro"));
                
                Livro liv = new LivroDAO().buscarPorId(livroID);
                Usuario user;
//                if (new UsuarioDAO().buscarPorNome(nome) == null) {
//                    user = new Usuario(nome.hashCode() % 100, nome, "123");
//                } else {
//                    user = new UsuarioDAO().buscarPorNome(nome);
//                }
                int id = new Random().nextInt();
                user = new Usuario(Math.abs(id), nome, "123");
                new UsuarioDAO().inserir(user);
                Usuario_avalia_Livro ual = new Usuario_avalia_Livro(user, liv, nota);
                

                new Usuario_avalia_LivroDAO().inserir(ual);
                request.getRequestDispatcher("/controller?opcao=livro.get-"+liv.getIdLivro()).forward(request, response);
            } catch (PersistenciaException ex) {
                Logger.getLogger(LivroController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        command = request.getParameter("command");
        System.out.println(command);
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

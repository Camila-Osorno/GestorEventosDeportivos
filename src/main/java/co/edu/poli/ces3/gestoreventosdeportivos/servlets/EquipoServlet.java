package co.edu.poli.ces3.gestoreventosdeportivos.servlets;

import co.edu.poli.ces3.gestoreventosdeportivos.dao.EquipoDAO;
import co.edu.poli.ces3.gestoreventosdeportivos.models.Equipo;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/equipos")
public class EquipoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final EquipoDAO equipoDAO = new EquipoDAO();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Leer JSON del cuerpo de la petición
        BufferedReader reader = request.getReader();
        Equipo nuevoEquipo = gson.fromJson(reader, Equipo.class);

        // Intentar agregar el equipo
        boolean agregado = equipoDAO.agregarEquipo(nuevoEquipo);

        if (agregado) {
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write(gson.toJson(nuevoEquipo));
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"El equipo ya existe o los datos son inválidos\"}");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Paginación
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int size = request.getParameter("size") != null ? Integer.parseInt(request.getParameter("size")) : 5;

        List<Equipo> equipos = equipoDAO.obtenerEquipos(page, size);
        response.getWriter().write(gson.toJson(equipos));
    }
}

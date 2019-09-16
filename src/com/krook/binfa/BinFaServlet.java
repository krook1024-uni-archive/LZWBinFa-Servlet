package com.krook.binfa;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.util.Map;

@WebServlet(name = "BinFaServlet", urlPatterns = {"/processForm"})
public class BinFaServlet extends HttpServlet {
    public static byte binaryOne = 0x0a;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.sendError(400, "POST metódus nem engedélyezett");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String input = "", save = "", filename = "", homedir = "";
        Map<String, String[]> params = request.getParameterMap();

        if (request.getParameterMap().containsKey("text")) {
            input = request.getParameter("text");
        } else {
            response.sendError(400, "Hibás bemenet!");
        }

        if (input.length() < 1) {
            response.sendError(400, "Hibás bemenet!");
        }

        if (params.containsKey("save")) {
            save = request.getParameter("save");

            if (save == "on" && params.containsKey("filename")) {
                filename = request.getParameter("filename");
                homedir = System.getProperty("user.home");
            } else {
                response.sendError(400, "Hiányzó fájlnév!");
            }
        }

        byte[] bajtok = input.getBytes();

        LZWBinFa binFa = new LZWBinFa();

        for (int j = 0; j < bajtok.length; ++ j) {
            if (bajtok[j] == 0x4e) {
                continue;
            }

            for (int i = 0; i < 8; ++ i) {
                if ((bajtok[j] & 0x80) != 0) {
                    binFa.addBit('1');
                } else {
                    binFa.addBit('0');
                }

                bajtok[j] <<= 1;
            }
        }

        if (save == "on") {
            PrintWriter kiFile = new PrintWriter(new BufferedWriter(new FileWriter(homedir + filename + ".txt")));
            binFa.printFa(kiFile);
            kiFile.println("melyseg = " + binFa.getMelyseg());
            kiFile.println("atlag = " + binFa.getAtlag());
            kiFile.println("szoras = " + binFa.getSzoras());
            kiFile.close();
        }

        binFa.printFaHtml(out);
        out.println("<p>melyseg = " + binFa.getMelyseg() + "</p>");
        out.println("<p>atlag = " + binFa.getAtlag() + "</p>");
        out.println("<p>szoras = " + binFa.getSzoras() + "</p>");
        out.flush();
    }
}

package Dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.conexion;
import Conexion.conexion;
import Modelo.Usuario;

public class DaoAlumno {
	conexion cx = null;

	public DaoAlumno() {
		cx = new conexion();

	}

	public boolean insertarUsuario(Usuario user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO usuario VALUES(null,?,?,?)");
			ps.setString(1, user.getUser());
			ps.setString(2, user.getPasword());
			ps.setString(3, user.getNombre());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}

	}

	public ArrayList<Usuario> fetchUsiarios() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM usuario");
			rs = ps.executeQuery();
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setUser(rs.getString("user"));
				u.setPasword(rs.getString("password"));
				u.setNombre(rs.getString("Nombre"));
				lista.add(u);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public boolean eliminarUsuario(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM usuario WHERE id=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}

	public boolean editarUsuario(Usuario user) {
		PreparedStatement ps = null;
		try {

			ps = cx.conectar().prepareStatement("UPDATE usuario SET user=?,password=?,nombre=? WHERE id=?");
			ps.setString(1, user.getUser());
			ps.setString(2, user.getPasword());
			ps.setString(3, user.getNombre());
			ps.setInt(4, user.getId());
			ps.executeUpdate();
			ps.executeUpdate();
			cx.desconectar();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}
}

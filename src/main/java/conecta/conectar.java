package conecta;
import java.sql.*;
import java.io.PrintWriter;

public class conectar {

    private static Connection conexao_MySql = null;
    private static String localBD = "localhost";
    private static String LINK = 
            "jdbc:mysql://" + localBD + ":3306/inkvibes";
    private static final String usuario = "root";
    private static final String senha = "Senai123";
    
    // Método para fazer a conexão com um banco de dados MySql
    public Connection connectionMySql() {
      try {
         conexao_MySql = 
                DriverManager.getConnection(LINK, usuario, senha);
          System.out.println("conexão OK!");
      } catch (SQLException e) {
         throw new 
        RuntimeException("Ocorreu um problema na conexão com o BD", e);
      }
       return conexao_MySql;
    }

      public String dataBaseSelect(int cod) {
        Connection connection = connectionMySql();
        String x = "";
        String sql = "select nome, email, telefone, endereco, senha "
                + "from usuarios "
                + "where codigo = ?";
        
        PreparedStatement preparedStmt;
        
        try {
            preparedStmt = connection.prepareStatement(sql);
            //Efetua a troca do '?' pelos valores na query
            preparedStmt.setInt(1, cod);
            ResultSet result = preparedStmt.executeQuery();
            //valida resultado
            while (result.next()) {
                int code = result.getInt("id");
                String name = result.getString("nome");
                String email = result.getString("email");
                String telefone = result.getString("telefone");
                String endereco = result.getString("endereco");
                String senha = result.getString("senha");
                System.out.println("cod: " + code);
                System.out.println("name: " + name);
                System.out.println("email : " + email);
                System.out.println("telefone : " + telefone);
                System.out.println("endereco : " + endereco);
                System.out.println("senha : " + senha);
                x = name;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return x;
    }
 public void closeConnectionMySql(Connection con) {
        try {
            if (con != null) {
                con.close();
                System.out.println("Fechamento OK");
            }
        } catch (SQLException e) {
            throw new 
        RuntimeException("Ocorreu um problema para encerrar "
                + "a conexão com o BD.", e);
        }
    }     

    public void dataBaseInsert(String Nome, String Email, String Telefone, String Endereco, String Senha) {
        Connection connection = connectionMySql();
        String sql = "INSERT INTO usuarios (id, nome, email, telefone, endereco, senha) VALUES (null,?,?,?,?,?)";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(sql);
            //Efetua a troca do '?' pelos valores na query 			
            preparedStmt.setString(1, Nome);
            preparedStmt.setString(2, Email);
            preparedStmt.setString(3, Telefone);
            preparedStmt.setString(4, Endereco);
            preparedStmt.setString(5, Senha);
            preparedStmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
         
     
    public boolean autenticarUsuario(String usuario, String senha) {
        if (usuario == null || senha == null || usuario.isEmpty() || senha.isEmpty()) {
            System.out.println("Usuário ou senha vazios.");
            return false;
        }

        try (Connection connection = connectionMySql()) {
            String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, usuario);
                preparedStatement.setString(2, senha);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Se houver pelo menos uma linha no resultado, o usuário é considerado autenticado
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao autenticar usuário: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

      public void consulta(Connection con) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from usuarios");
            System.out.println("Consulta ao banco:");
            while (rs.next()) {
                System.out.println("cod: " + rs.getInt(1) + " - Nome: " + rs.getString(2) + " - Email: " + rs.getString(3)
                        + " - codcidade: " + rs.getInt(4));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }



}

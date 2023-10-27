package conecta;
import java.sql.*;

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
        String sql = "select nome, email, codigo "
                + "from aluno "
                + "where codigo = ?";
        
        PreparedStatement preparedStmt;
        
        try {
            preparedStmt = connection.prepareStatement(sql);
            //Efetua a troca do '?' pelos valores na query
            preparedStmt.setInt(1, cod);
            ResultSet result = preparedStmt.executeQuery();
            //valida resultado
            while (result.next()) {
                int code = result.getInt("codigo");
                String name = result.getString("nome");
                String name2 = result.getString("email");
                System.out.println("cod: " + code);
                System.out.println("name: " + name);
                System.out.println("email : " + name2);
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

    public void dataBaseInsert(String Nome,int telefone, String Email, int cpf, String endereco, String senha) {
        Connection connection = connectionMySql();
        String sql = "INSERT INTO usuarios (nome, telefone, email, cpf, endereco, senha) VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(sql);
            //Efetua a troca do '?' pelos valores na query 			
            preparedStmt.setString(1, Nome);
            preparedStmt.setInt(2, telefone);
            preparedStmt.setString(3, Email);
            preparedStmt.setInt(4, cpf);
            preparedStmt.setString(5, endereco);
            preparedStmt.setString(6, senha);
            preparedStmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
         
    
    
    
    
      public void consulta(Connection con) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from usuarios");
            System.out.println("Consulta ao banco:");
            while (rs.next()) {
                System.out.println("nome: " + rs.getString(1) + " - Telefone: " + rs.getInt(2) + " - Email: " + rs.getString(3)
                        + " - CPF: " + rs.getString(4) + " - Endereço: " + rs.getString(5) + " - Senha: "+ rs.getString(6));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}

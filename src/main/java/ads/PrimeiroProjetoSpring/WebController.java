package ads.PrimeiroProjetoSpring;

import conecta.conectar;
import java.sql.Connection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pro.mongocrud.ConectarMongo;

@Controller
public class WebController {
    @RequestMapping("/form")
    public String DigaOla2(Model modelo){
        System.out.println("Estou no form");
        modelo.addAttribute("mensagem2", "Cadastro");
        return "form"; 
    }
    
    @RequestMapping(value="/respostaForm", method=RequestMethod.POST)
    public String DigaOla3(Model modelo, String fname, int tel, String email, int cpf, String end, String senha){
        System.out.println("Agora estou na Resposta Form");
        modelo.addAttribute("mensagem3", "Resposta do Formulário:\n" + fname + " seja bem vindo\n" + "seu email é: " + email);
        //Conexão com o mongo
        ConectarMongo con = new ConectarMongo();
        con.insertValues(fname, tel, email, cpf, end, senha);
        con.getValues();
        //Conexão com o MySQL
        conectar obj = new conectar();
        Connection conexao = obj.connectionMySql();
        obj.dataBaseInsert(fname, tel, email, cpf, end, senha);
        obj.consulta(conexao);
        obj.closeConnectionMySql(conexao);
        return "respostaForm";
    }
}


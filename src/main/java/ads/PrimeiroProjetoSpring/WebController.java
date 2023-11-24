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
    @RequestMapping("/pag1")
    public String DigaOla(Model modelo){
        System.out.println("Dizendo Olárrr!");
        modelo.addAttribute("mensagem", "Bem vind@ a essa loucura!");
        return "pag1"; 
    }
    @RequestMapping("/form")
    public String DigaOla2(Model modelo){
        System.out.println("Estou no form");
        modelo.addAttribute("mensagem2", "Formulário");
        return "form"; 
    }
    
    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String Cadastrar(Model modelo, String nomeCad, String email, String telefone, String endereco, String senha){
        System.out.println("Agora estou no Resposta Form");
        
        modelo.addAttribute("script", "<script>alert('Cadastrado com sucesso, agora faça o login: "+ nomeCad+"'); window.location.href='/login.html';</script>");
        //modelo.addAttribute("mensagem3", "Cadastrado com sucesso, agora faça o login: "+ nomeCad);
        
        //conexão com MySQL - insere dados
        conectar obj = new conectar();
        Connection conexao = obj.connectionMySql();
        obj.dataBaseInsert(nomeCad, email, telefone, endereco, senha);
        obj.consulta(conexao);
        obj.closeConnectionMySql(conexao);
        
        return "login";
    }
    
    
    
    @RequestMapping(value="/indexlogado", method = RequestMethod.POST)
    public String Logar(Model modelo, String email, String senhaLog){
        System.out.println("Logando");
        modelo.addAttribute("mensagem4", "Logado com sucesso: "+ email);
        conectar obj = new conectar();
        Connection conexao = obj.connectionMySql();
        
        if(obj.autenticarUsuario(email, senhaLog)){
            modelo.addAttribute("mensagem4", "Logado com sucesso: "+ email);
            obj.consulta(conexao);
            obj.closeConnectionMySql(conexao);
            return "indexlogado";
        }else{
            modelo.addAttribute("mensagem4", "Login inválido");
            obj.consulta(conexao);
            obj.closeConnectionMySql(conexao);
            return "login";
        }
    }
}


package rs.ac.etf.pp1.js150411d;

import java_cup.runtime.Symbol;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import rs.ac.etf.pp1.js150411d.util.Log4JUtils;
import rs.ac.etf.pp1.js150411d.lexer.Yylex;
import rs.ac.etf.pp1.js150411d.lexer.sym;

import java.io.*;

public class MJTest {
    static {
        DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
        Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
    }

    public static void main(String [] args) throws IOException {
        Logger log = Logger.getLogger(MJTest.class);
        Reader br = null;
        try {
            File sourceCode = new File("src\\test\\resources\\program.mj");
            log.info("Compiling source file:" + sourceCode.getAbsolutePath());

            br = new BufferedReader(new FileReader(sourceCode));

            Yylex yylex = new Yylex(br);
            Symbol currToken;
            while ((currToken = yylex.next_token()).sym != sym.EOF){
                if(currToken != null && currToken.value != null)
                    log.info(currToken.toString() + " " + currToken.value.toString());
            }

        }finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }
}

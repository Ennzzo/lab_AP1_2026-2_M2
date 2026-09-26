
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Os testes prontos servem de exemplo.
 * Complete os testes marcados com //TODO (Tarefas 4 e 5).
 */
public class EstacaoTest {

    private Estacao estacao;

    @BeforeEach
    void setUp() {
        estacao = new Estacao("E1");
    }

    @Test
    void deveAdicionarPatineteNovo() {
        Patinete p = new Patinete("1234", 80);
        assertTrue(estacao.adicionar(p), "deveria adicionar");
        assertEquals(1, estacao.totalPatinetes());
    }

    @Test
    void naoDeveAdicionarCodigoDuplicado() {
        assertTrue(estacao.adicionar(new Patinete("1234", 80)));
        assertFalse(estacao.adicionar(new Patinete("1234", 50)),
                "codigo duplicado deve ser rejeitado");
        assertEquals(1, estacao.totalPatinetes());
    }

    @Test
    void deveContarDisponiveisEEmUso() {
        estacao.adicionar(new Patinete("1111", 80));
        estacao.adicionar(new Patinete("2222", 10));
        Patinete emUso = new Patinete("3333", 90);
        estacao.adicionar(emUso);
        emUso.iniciarAluguel();
        assertEquals(1, estacao.totalDisponiveis());
        assertEquals(1, estacao.totalEmUso());
    }

    @Test
    void resumoDeveConterCodigoETotais() {
        estacao.adicionar(new Patinete("1234", 80));
        String r = estacao.resumo();
        assertTrue(r.contains("E1"), r);
        assertTrue(r.contains("total=1"), r);
        assertTrue(r.contains("disp=1"), r);
        assertTrue(r.contains("uso=0"), r);
    }

    @Test
    void deveCalcularAproveitamentoFrota() {
        Patinete patinete1 = new Patinete("1234", 100);
        Patinete patinete2 = new Patinete("4321", 100);

        estacao.adicionar(patinete1);
        estacao.adicionar(patinete2);
        patinete1.iniciarAluguel();

        double aproveitamentoDaFrota = estacao.aproveitamentoFrota();

        assertEquals(0.50d, aproveitamentoDaFrota, 0.01d);
    }

    @Test
    void deveCompararEstacoes() {
        Estacao estacao1 = new Estacao("A1");
        Patinete patinete1 = new Patinete("1234", 80);
        Patinete patinete2 = new Patinete("4321", 90);
        Patinete patinete3 = new Patinete("1111", 100);
        estacao.adicionar(patinete1);
        estacao.adicionar(patinete2);
        estacao.adicionar(patinete3);

        boolean estaNaFrente = estacao.estaNaFrenteDe(estacao1);

        assertTrue(estaNaFrente);
    }
}

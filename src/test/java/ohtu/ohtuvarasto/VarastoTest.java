package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto turhaVarasto;
    Varasto varastoAlkusaldolla;
    Varasto turhaAlkusaldolla;
    Varasto varastoYlitaytto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        turhaVarasto = new Varasto(-10);
        varastoAlkusaldolla = new Varasto(10, 5);
        turhaAlkusaldolla = new Varasto(-10, -10);
        varastoYlitaytto = new Varasto(10, 20);
        
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoVarastonAlkusaldolla() {
        assertEquals(5, varastoAlkusaldolla.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varastoAlkusaldolla.getTilavuus(),vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uusiVarastoEiYlitayty() {
        assertEquals(10, varastoYlitaytto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktorillaVoiLuodaKayttokelvottomanVaraston() {
        assertEquals(0, turhaVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriVoiLuodaKayttokelvottomanVarastonSaldolla() {
        assertEquals(0, turhaAlkusaldolla.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, turhaAlkusaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test 
    public void negatiivinenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-8);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysTayttaaVainMaksimiin() {
        varasto.lisaaVarastoon(15);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void poistetaanNegatiivinenMaara() {
        varasto.lisaaVarastoon(5);
        assertEquals(0, varasto.otaVarastosta(-5), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaSaadaanKaikkiMitaSiellaOnVarastoTyhjenee() {
        assertEquals(5, varastoAlkusaldolla.otaVarastosta(10), vertailuTarkkuus);
        assertEquals(0, varastoAlkusaldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringMetodiPalauttaaOikeassaMuodossa() {
        assertEquals("saldo = 5.0, vielä tilaa 5.0", varastoAlkusaldolla.toString());
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}
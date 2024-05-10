package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primary;
  private TorpedoStore secondary;

  @BeforeEach
  public void init(){
    primary = mock(TorpedoStore.class);
    secondary = mock(TorpedoStore.class);
    //when(primary.fire(1).thenReturn(true));


    //verify(primary.fire(1));
    //verify(secondary.fire(1));

    //when(primary)
    this.ship = new GT4500(primary,secondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    doReturn(true).when(primary).fire(1);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    //verify(ship.fireTorpedo(FiringMode.SINGLE));
    verify(primary, times(1)).fire(1);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    doReturn(true).when(primary).fire(1);
    doReturn(true).when(secondary).fire(1);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    verify(primary, times(1)).fire(1);
    verify(secondary, times(1)).fire(1);

    //verify(ship.fireTorpedo(FiringMode.ALL));



    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_Twice(){
    doReturn(true).when(primary).fire(1);
    doReturn(true).when(secondary).fire(1);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    verify(primary, times(1)).fire(1);
    verify(secondary, times(1)).fire(1);

    assertEquals(true, result);
    assertEquals(true, result2);
  }

  @Test
  public void fireTorpedo_Single_Twice_SecondaryEmpty(){
    doReturn(true).when(primary).fire(1);
    doReturn(true).when(secondary).fire(1);

    doReturn(true).when(secondary).isEmpty();

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    verify(primary, times(2)).fire(1);
    verify(secondary, times(0)).fire(1);

    assertEquals(true, result);
    assertEquals(true, result2);

  }

  @Test
  public void fireTorpedo_Single_Twice_BothEmpty(){
    doReturn(true).when(primary).fire(1);
    doReturn(true).when(secondary).fire(1);

    doReturn(true).when(secondary).isEmpty();
    doReturn(true).when(primary).isEmpty();

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(primary, times(0)).fire(1);
    verify(secondary, times(0)).fire(1);

    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Single_Twice_FirstReportsFailure(){
    doReturn(false).when(primary).fire(1);
    doReturn(false).when(secondary).fire(1);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    verify(primary, times(1)).fire(1);
    verify(secondary, times(1)).fire(1);

    assertEquals(false, result);
    assertEquals(false, result2);
  }

  @Test
  public void fireTorpedo_Single_Twice_SecondaryEmptyAfterOneShot(){
    doReturn(true).when(primary).fire(1);
    doReturn(true).when(secondary).fire(1);

    doReturn(false).when(secondary).isEmpty();
    doReturn(false).when(primary).isEmpty();

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    doReturn(true).when(secondary).isEmpty();

    boolean result3 = ship.fireTorpedo(FiringMode.SINGLE);

    verify(primary, times(2)).fire(1);
    //verify(secondary, times(1)).fire(1);

    assertEquals(true, result);
    assertEquals(true, result3);

  }
  

}

package org.duofold.live;
import org.junit.Test;
import static org.junit.Assert.*;
public class FrostPolicyTest {
 @Test public void zeroFrostIsTheOriginalBlackFade(){
  for(float a=0;a<=1;a+=.1f){assertEquals(a,FrostPolicy.dim(a,0),1e-6);assertEquals(0,FrostPolicy.radius(a,0));}
 }
 @Test public void fullFrostBlursFirstAndKeepsHalfDim(){
  assertEquals(0,FrostPolicy.radius(0,1));
  assertEquals(FrostPolicy.MAX_RADIUS,FrostPolicy.radius(.5f,1));
  assertEquals(FrostPolicy.MAX_RADIUS,FrostPolicy.radius(1,1));
  assertTrue(FrostPolicy.radius(.25f,1)>0&&FrostPolicy.radius(.25f,1)<FrostPolicy.MAX_RADIUS);
  assertEquals(.5f,FrostPolicy.dim(1,1),1e-6);
 }
 @Test public void blurIsMonotonicSoDefrostNeverPulses(){
  int previous=-1;for(int i=0;i<=100;i++){int r=FrostPolicy.radius(i/100f,.7f);assertTrue(r>=previous);previous=r;}
 }
 @Test public void invalidInputIsSafe(){
  assertEquals(0,FrostPolicy.radius(Float.NaN,1));
  assertEquals(0,FrostPolicy.dim(1,Float.NaN)-1,1e-6);
  assertEquals(1,FrostPolicy.strength(5),0);
 }
}

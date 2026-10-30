package org.duofold.live;
/**
 * Frosted handoff: maps the existing black-fade opacity onto a compositor blur
 * plus a lighter dim. Timing, readiness and reveal stay owned by HandoffFadePolicy.
 * frost=0 reproduces the original pure black fade exactly.
 */
final class FrostPolicy {
 static final float DEFAULT=0f;
 /** Strongest blur in pixels at full frost. */
 static final int MAX_RADIUS=160;
 /** At full frost the screen keeps this fraction of black instead of going fully dark. */
 static final float MAX_DIM_RELIEF=.5f;
 /** Blur reaches full strength over the first part of the fade, before the dim. */
 static final float BLUR_LEAD=.5f;
 static float strength(float v){return Float.isFinite(v)?Math.max(0,Math.min(1,v)):DEFAULT;}
 /** Opacity of the black layer for fade opacity a. */
 static float dim(float a,float frost){
  a=Math.max(0,Math.min(1,a));frost=strength(frost);
  return a*(1-MAX_DIM_RELIEF*frost);
 }
 /** Background blur radius for fade opacity a; 0 when frost is off. */
 static int radius(float a,float frost){
  frost=strength(frost);if(frost<=0||!(a>0))return 0;
  float x=Math.min(1,a/BLUR_LEAD);x=x*x*(3-2*x);
  return Math.round(MAX_RADIUS*frost*x);
 }
 private FrostPolicy(){}
}

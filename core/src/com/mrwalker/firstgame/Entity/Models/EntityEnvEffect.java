package com.mrwalker.firstgame.Entity.Models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class EntityEnvEffect {
    /*
        Entity Environmental Effects
     */

    private short realSpeed = 0;
    private short noise = 0;  //representation of noise in circle radius
    private float noiseMultiplayer = 0.5f;

    private short earshot = 100;
    private short defaultEarshotValue = 100;

    private long periodMillis = 0;
    private long startTime = 0;

    private boolean allowChangeNoise(){
        if (TimeUtils.timeSinceMillis(startTime) > periodMillis)
            return true;
        return false;
    }

    private void setNoise() {
        if (allowChangeNoise())
            this.noise = (short) (realSpeed * noiseMultiplayer);
    }

    public void setNoiseFor(short noise, long periodMillis) {
        startTime = TimeUtils.millis();
        this.periodMillis = periodMillis;
        this.noise = noise;
    }

    public short getNoise() {
        return noise;
    }

    public short getRealSpeed() {
        return realSpeed;
    }

    public void setRealSpeed(Vector2 velocity) {
        double temp = Math.sqrt(Math.pow(velocity.x, 2) + Math.pow(velocity.y, 2));
        if (temp > 120) temp = 120;
        this.realSpeed =  (short) temp;
        setNoise();
    }

    public float getNoiseMultiplayer() {
        return noiseMultiplayer;
    }

    public void setNoiseMultiplayer(float noiseMultiplayer) {
        this.noiseMultiplayer = noiseMultiplayer;
    }

    public short getEarshot() {
        return earshot;
    }

    public void setEarshot(short earshot) {
        this.earshot = earshot;
    }

    public void setDefaultEarshot(){
        this.earshot = defaultEarshotValue;
    }

    public void setDefaultEarshotValue(short earshot){
        this.defaultEarshotValue = earshot;
    }
}

package net.irrelevent.neardeadlythreat.gun;


import net.minecraft.client.render.Camera;

//future update
public class GunSystem {
    public int damage;
    public float timeBetweenShooting, spread, range, reloadTime, timeBetweenShots;
    public int magazineSize, bulletsPerTap;
    public boolean allowButtonHold;
    int bulletsLeft, bulletsShot;

    boolean shooting, readyToShoot, reloading;

    public Camera fpsCam;


}

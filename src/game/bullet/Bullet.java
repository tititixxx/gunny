package game.bullet;

import base.GameObject;
import base.Vector2D;
import renderer.ImageRenderer;

public class Bullet extends GameObject {
    public Vector2D velocity;

    public Bullet() {
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/bullet.png",50,25);
    }

    @Override
    public void run(){
        super.run();
        this.position.addUp(this.velocity);
    }
}

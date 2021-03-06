package base;
import game.physic.BoxCollider;
import game.physic.PhysicBody;
import game.player.playerLeft.PlayerLeft;
import game.player.playerRight.PlayerRight;
import game.viewFinder.ViewFinderLeft;
import game.viewFinder.ViewFinderRight;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameObjectManager {

    public static GameObjectManager instance = new GameObjectManager();

    private List<GameObject> list;
    private List<GameObject> tempList;

    private GameObjectManager() {
        this.list = new ArrayList<>();
        this.tempList = new ArrayList<>();

    }

    public void add(GameObject gameObject) {
        this.tempList.add(gameObject);
    }

    public void runAll() {
        this.list
                .stream()
                .filter(gameObject -> gameObject.isAlive)
                .forEach(gameObject -> gameObject.run());
        this.list.addAll(this.tempList);
        this.tempList.clear();
    }

    public void renderAll(Graphics graphics) {
        this.list
                .stream()
                .filter(gameObject -> gameObject.isAlive)
                .forEach(gameObject -> gameObject.render(graphics));
    }

    public <T extends GameObject> T checkCollision(BoxCollider boxCollider, Class<T> cls) {
        return (T) this.list
                .stream()
                .filter(gameObject -> gameObject.isAlive)
                .filter(gameObject -> cls.isInstance(gameObject))
                .filter(gameObject -> gameObject instanceof PhysicBody)
                .filter(gameObject -> {
                    BoxCollider other = ((PhysicBody)gameObject).getBoxCollider();
                    return boxCollider.checkBoxCollider(other);
                })
                .findFirst()
                .orElse(null);
    }

    public <T extends GameObject> T recycle(Class<T> cls) {
        T object = (T) this.list
                .stream()
                .filter(gameObject -> !gameObject.isAlive)
                .filter(gameObject -> cls.isInstance(gameObject))
                .findFirst()
                .orElse(null);
        if (object != null) {
            object.isAlive = true;
        } else {
            try {
                object = cls.newInstance();
                this.add(object);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return object;
    }


    public GameObject findCharacter(String side) {
        return (GameObject) this.list
                .stream()
                .filter(gameObject -> gameObject instanceof GameObject)
                .filter(gameObject -> gameObject.side == side)
                .findFirst()
                .orElse(null);
    }

    public PlayerLeft findPlayerLeft(String side) {
        return (PlayerLeft) this.list
                .stream()
                .filter(gameObject -> gameObject instanceof PlayerLeft)
                .filter(gameObject -> gameObject.side == side)
                .findFirst()
                .orElse(null);
    }

    public PlayerRight findPlayerRight(String side) {
        return (PlayerRight) this.list
                .stream()
                .filter(gameObject -> gameObject instanceof PlayerRight)
                .filter(gameObject -> gameObject.side == side)
                .findFirst()
                .orElse(null);
    }

    public ViewFinderLeft findViewFinderLeft(String side) {
        return (ViewFinderLeft) this.list
                .stream()
                .filter(gameObject -> gameObject instanceof ViewFinderLeft)
                .filter(gameObject -> gameObject.side == side)
                .findFirst()
                .orElse(null);
    }

    public ViewFinderRight findViewFinderRight(String side) {
        return (ViewFinderRight) this.list
                .stream()
                .filter(gameObject -> gameObject instanceof ViewFinderRight)
                .filter(gameObject -> gameObject.side == side)
                .findFirst()
                .orElse(null);
    }

    public void clear() {
        this.list.clear();
        this.tempList.clear();
    }
}

  package Main;

import java.util.ArrayList;

import object.ItemEntity;
import object.Rupee;

public class CollisionChecker 
{

	private GamePanel gp; //game panel
	private RoomManager rooms; //rooms
	private int curRow; //current row
	private int curCol; //current column
	
	public CollisionChecker(GamePanel gp, RoomManager rooms, int curRow, int curCol)
	{
		
		this.gp = gp;
		this.rooms = rooms;
		this.curRow = curRow;
		this.curCol = curCol;
		
		
	}
	
	public void setCurRow(int n)
	{
		curRow = n;
	}
	
	public void setCurCol(int n)
	{
		curCol = n;
	}
	
	
	/**
	 * Checks the collision with tiles for the given entity.
	 *
	 * @param entity The entity to check collision for.
	 *
	 * This method calculates the positions and indices of the entity's bounding box within the tile grid.
	 * It then checks the corresponding tiles in the current room for collision.
	 * If a collision is detected, the entity's collision flag is set to true.
	 * If the entity encounters a traversable tile, a special action may be triggered.
	 */
	public void checkTile(Entity entity) {
	    // Calculate entity's bounding box positions
	    int entityLeftX = entity.getX() + entity.getSolidArea().x;
	    int entityRightX = entity.getX() + entity.getSolidArea().x + entity.getSolidArea().width;
	    int entityTopY = entity.getY() + entity.getSolidArea().y;
	    int entityBottomY = entity.getY() + entity.getSolidArea().y + entity.getSolidArea().height;

	    // Calculate entity's tile indices
	    int entityLeftCol = entityLeftX / gp.tileSize;
	    int entityRightCol = entityRightX / gp.tileSize;
	    int entityTopRow = entityTopY / gp.tileSize;
	    int entityBottomRow = entityBottomY / gp.tileSize;

	    // Get the current room
	    Room curRoom = rooms.getRoomArray().get(curRow).get(curCol);

	    int tileNum1;
	    int tileNum2;

	    // Handle collision based on entity's direction
	    switch (entity.getDirection()) {
	        case "up":
	            if (entityRightCol < 16) {
	                entityTopRow = (entityTopY - entity.getSpeed()) / gp.tileSize;
	                tileNum1 = curRoom.getTileLayout()[entityTopRow][entityLeftCol];
	                tileNum2 = curRoom.getTileLayout()[entityTopRow][entityRightCol];
	                if (curRoom.getTile()[tileNum1].getCollision() || curRoom.getTile()[tileNum2].getCollision()) {
	                    entity.setCollisionOn(true);
	                }
	                if (curRoom.getTile()[tileNum1].getTraverse() || curRoom.getTile()[tileNum2].getTraverse()) {
	                    gp.getKeyHandler().setOn(false);
	                    gp.getKeyHandler().setUpPressed(false);
	                    int thisX = gp.getPlayer().getX();
	                    int thisY = gp.getPlayer().getY();
	                    for (int i = 0; i < 48; i++) {
	                        rooms.setCurrentRoom(rooms.getSwitchedRoom(curRoom, new Room(gp), i, "cave"));
	                        gp.repaint();
	                    }
	                    rooms.setCurrentRoomCol(curRoom.getToCaveC());
	                    rooms.setCurrentRoomRow(curRoom.getToCaveR());
	                    gp.getPlayer().setX(362);
	                    gp.getPlayer().setY(518);
	                }
	            }
	            break;
	        case "down":
	            entityBottomRow = (entityBottomY + entity.getSpeed()) / gp.tileSize;
	            if (entityBottomRow < 12 && entityRightCol < 16) {
	                tileNum1 = curRoom.getTileLayout()[entityBottomRow][entityLeftCol];
	                tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityRightCol];
	                if (curRoom.getTile()[tileNum1].getCollision() || curRoom.getTile()[tileNum2].getCollision()) {
	                    entity.setCollisionOn(true);
	                }
	                if (curRoom.getTile()[tileNum1].getTraverse() || curRoom.getTile()[tileNum2].getTraverse()) {
	                    // Handle special action for traversable tile
	                }
	            }
	            break;
	        case "left":
	            if (entityBottomRow < 12) {
	                entityLeftCol = (entityLeftX - entity.getSpeed()) / gp.tileSize;
	                tileNum1 = curRoom.getTileLayout()[entityTopRow][entityLeftCol];
	                tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityLeftCol];
	                if (curRoom.getTile()[tileNum1].getCollision() || curRoom.getTile()[tileNum2].getCollision()) {
	                    entity.setCollisionOn(true);
	                }
	            }
	            break;
	        case "right":
	            entityRightCol = (entityRightX + entity.getSpeed()) / gp.tileSize;
	            if (entityRightCol < 16 && entityBottomRow < 12) {
	                tileNum1 = curRoom.getTileLayout()[entityTopRow][entityRightCol];
	                tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityRightCol];
	                if (curRoom.getTile()[tileNum1].getCollision() || curRoom.getTile()[tileNum2].getCollision()) {
	                    entity.setCollisionOn(true);
	                }
	            }
	            break;
	    }
	}

	
	
	/**
	 * Checks the collision with tiles for the given entity when it is knocked back.
	 *
	 * @param entity The entity to check collision for.
	 *
	 * This method calculates the positions and indices of the entity's bounding box within the tile grid.
	 * It then checks the corresponding tiles in the current room for collision when the entity is knocked back.
	 * If a collision is detected, the entity's collision flag is set to true.
	 */
	public void checkKnockTile(Entity entity) {
	    // Calculate entity's bounding box positions
	    int entityLeftX = entity.getX() + entity.getSolidArea().x;
	    int entityRightX = entity.getX() + entity.getSolidArea().x + entity.getSolidArea().width;
	    int entityTopY = entity.getY() + entity.getSolidArea().y;
	    int entityBottomY = entity.getY() + entity.getSolidArea().y + entity.getSolidArea().height;

	    // Calculate entity's tile indices
	    int entityLeftCol = entityLeftX / gp.tileSize;
	    int entityRightCol = entityRightX / gp.tileSize;
	    int entityTopRow = entityTopY / gp.tileSize;
	    int entityBottomRow = entityBottomY / gp.tileSize;

	    // Get the current room
	    Room curRoom = rooms.getRoomArray().get(curRow).get(curCol);

	    int tileNum1;
	    int tileNum2;

	    switch (entity.getKnockedDir()) {
	        case "up":
	            if (entityRightCol < 16) {
	                entityTopRow = (entityTopY - entity.getSpeed()) / gp.tileSize;
	                tileNum1 = curRoom.getTileLayout()[entityTopRow][entityLeftCol];
	                tileNum2 = curRoom.getTileLayout()[entityTopRow][entityRightCol];
	                if (curRoom.getTile()[tileNum1].getCollision() || curRoom.getTile()[tileNum2].getCollision()) {
	                    entity.setCollisionOn(true);
	                }
	            }
	            break;
	        case "down":
	            entityBottomRow = (entityBottomY + entity.getSpeed()) / gp.tileSize;
	            if (entityBottomRow < 12 && entityRightCol < 16) {
	                tileNum1 = curRoom.getTileLayout()[entityBottomRow][entityLeftCol];
	                tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityRightCol];
	                if (curRoom.getTile()[tileNum1].getCollision() || curRoom.getTile()[tileNum2].getCollision()) {
	                    entity.setCollisionOn(true);
	                }
	                if (curRoom.getTile()[tileNum1].getTraverse() || curRoom.getTile()[tileNum2].getTraverse()) {
	                    // Handle special action for traversable tile
	                }
	            }
	            break;
	        case "left":
	            if (entityBottomRow < 12) {
	                entityLeftCol = (entityLeftX - entity.getSpeed()) / gp.tileSize;
	                tileNum1 = curRoom.getTileLayout()[entityTopRow][entityLeftCol];
	                tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityLeftCol];
	                if (curRoom.getTile()[tileNum1].getCollision() || curRoom.getTile()[tileNum2].getCollision()) {
	                    entity.setCollisionOn(true);
	                }
	            }
	            break;
	        case "right":
	            entityRightCol = (entityRightX + entity.getSpeed()) / gp.tileSize;
	            if (entityRightCol < 16 && entityBottomRow < 12) {
	                tileNum1 = curRoom.getTileLayout()[entityTopRow][entityRightCol];
	                tileNum2 = curRoom.getTileLayout()[entityBottomRow][entityRightCol];
	                if (curRoom.getTile()[tileNum1].getCollision() || curRoom.getTile()[tileNum2].getCollision()) {
	                    entity.setCollisionOn(true);
	                }
	            }
	            break;
	    }
	}

	
	
	/**
	 * Checks if the player is hitting any object and returns the index of the object.
	 *
	 * @param player The player to check for hits.
	 * @return The index of the object that the player is hitting, or -1 if no hit is detected.
	 *
	 * This method checks if the player's solid area intersects with any of the objects in the current room.
	 * If a hit is detected, the player's health is reduced, and the player is knocked back in the opposite direction.
	 * The method also resets the positions of the player and the object after the collision check.
	 */
	public boolean checkFight(Player player) {
	    int index = -1;
	    ArrayList<Entity> mobs = gp.getRooms().getCurrentRoom().getMobs();

	    for (int i = 0; i < mobs.size(); i++) {
	        Entity mob = mobs.get(i);
	        if (mob != null) {
	            // Get the player's position
	            int playerX = player.getSolidArea().x;
	            int playerY = player.getSolidArea().y;

	            player.getSolidArea().x = player.getX() + player.getSolidArea().x;
	            player.getSolidArea().y = player.getY() + player.getSolidArea().y;

	            // Get the mob's position
	            int mobX = mob.getSolidArea().x;
	            int mobY = mob.getSolidArea().y;

	            mob.getSolidArea().x = mob.getX() + mob.getSolidArea().x;
	            mob.getSolidArea().y = mob.getY() + mob.getSolidArea().y;

	            // Check for collision based on player's direction
	            switch (player.getDirection()) {
	                case "up":
	                    if (player.getSolidArea().intersects(mob.getSolidArea())) {
	                        player.setHealth(player.getHealth() - 1);
	                        player.setKnocked(true);
	                        player.setKnockedDir("down");
	                        System.out.println("hurt");
	                        player.getSolidArea().x = playerX;
	                        player.getSolidArea().y = playerY;
	                        mob.getSolidArea().x = mobX;
	                        mob.getSolidArea().y = mobY;
	                        return true;
	                    }
	                    break;
	                case "down":
	                    player.getSolidArea().y += player.getSpeed();
	                    if (player.getSolidArea().intersects(mob.getSolidArea())) {
	                        player.setHealth(player.getHealth() - 1);
	                        player.setKnocked(true);
	                        player.setKnockedDir("up");
	                        System.out.println("hurt");
	                        player.getSolidArea().x = playerX;
	                        player.getSolidArea().y = playerY;
	                        mob.getSolidArea().x = mobX;
	                        mob.getSolidArea().y = mobY;
	                        return true;
	                    }
	                    break;
	                case "left":
	                    player.getSolidArea().x -= player.getSpeed();
	                    if (player.getSolidArea().intersects(mob.getSolidArea())) {
	                        player.setHealth(player.getHealth() - 1);
	                        player.setKnocked(true);
	                        player.setKnockedDir("right");
	                        System.out.println("hurt");
	                        player.getSolidArea().x = playerX;
	                        player.getSolidArea().y = playerY;
	                        mob.getSolidArea().x = mobX;
	                        mob.getSolidArea().y = mobY;
	                        return true;
	                    }
	                    break;
	                case "right":
	                    player.getSolidArea().x += player.getSpeed();
	                    if (player.getSolidArea().intersects(mob.getSolidArea())) {
	                        player.setHealth(player.getHealth() - 1);
	                        player.setKnocked(true);
	                        player.setKnockedDir("left");
	                        System.out.println("hurt");
	                        player.getSolidArea().x = playerX;
	                        player.getSolidArea().y = playerY;
	                        mob.getSolidArea().x = mobX;
	                        mob.getSolidArea().y = mobY;
	                        return true;
	                    }
	                    break;
	            }

	            player.getSolidArea().x = player.getSolidAreaDefX();
	            player.getSolidArea().y = player.getSolidAreaDefY();
	            mob.getSolidArea().x = mob.getSolidAreaDefX();
	            mob.getSolidArea().y = mob.getSolidAreaDefY();
	        }

	        if (mob.getHealth() <= 0) {
	            mobs.remove(i);
	        }
	    }

	    gp.setMobs(mobs);
	    return false;
	}

	
	/**
	 * Checks if the sword is hitting any object and returns the index of the object.
	 *
	 * @param sword The sword to check for hits.
	 * @return The index of the object that the sword is hitting, or -1 if no hit is detected.
	 *
	 * This method checks if the sword's solid area intersects with any of the objects in the current room.
	 * If a hit is detected, the health of the object is reduced, and the object is knocked back in the opposite direction.
	 * The method also resets the positions of the sword and the object after the collision check.
	 */
	public int checkSword(Sword sword) {
	    int index = -1;
	    ArrayList<Entity> mobs = gp.getRooms().getCurrentRoom().getMobs();

	    for (int i = 0; i < mobs.size(); i++) {
	        Entity mob = mobs.get(i);
	        if (mob != null) {
	            // Get the sword's position
	            sword.getSolidArea().x = sword.getX() + sword.getSolidArea().x;
	            sword.getSolidArea().y = sword.getY() + sword.getSolidArea().y;

	            // Get the mob's position
	            mob.getSolidArea().x = mob.getX() + mob.getSolidArea().x;
	            mob.getSolidArea().y = mob.getY() + mob.getSolidArea().y;

	            // Check for collision based on sword's direction
	            switch (sword.getDirection()) {
	                case "up":
	                    if (sword.getSolidArea().intersects(mob.getSolidArea())) {
	                        System.out.println("attack");
	                        mob.setHealth(mob.getHealth() - 1);
	                        mob.setKnocked(true);
	                        mob.setKnockedDir("up");
	                        index = i;
	                    }
	                    break;
	                case "down":
	                    sword.getSolidArea().y += sword.getSpeed();
	                    if (sword.getSolidArea().intersects(mob.getSolidArea())) {
	                        System.out.println("attack");
	                        mob.setHealth(mob.getHealth() - 1);
	                        mob.setKnocked(true);
	                        mob.setKnockedDir("down");
	                        index = i;
	                    }
	                    break;
	                case "left":
	                    sword.getSolidArea().x -= sword.getSpeed();
	                    if (sword.getSolidArea().intersects(mob.getSolidArea())) {
	                        System.out.println("attack");
	                        mob.setHealth(mob.getHealth() - 1);
	                        mob.setKnocked(true);
	                        mob.setKnockedDir("left");
	                        index = i;
	                    }
	                    break;
	                case "right":
	                    sword.getSolidArea().x += sword.getSpeed();
	                    if (sword.getSolidArea().intersects(mob.getSolidArea())) {
	                        System.out.println("attack");
	                        mob.setHealth(mob.getHealth() - 1);
	                        mob.setKnocked(true);
	                        mob.setKnockedDir("right");
	                        index = i;
	                    }
	                    break;
	            }

	            sword.getSolidArea().x = sword.getSolidAreaDefX();
	            sword.getSolidArea().y = sword.getSolidAreaDefY();
	            mob.getSolidArea().x = mob.getSolidAreaDefX();
	            mob.getSolidArea().y = mob.getSolidAreaDefY();
	        }

	        if (mob.getHealth() <= 0) {
	            gp.getRooms().getCurrentRoom().addItem(new Rupee(gp, mob.getX(), mob.getY()));
	            mobs.remove(i);
	        }
	    }

	    gp.setMobs(mobs);
	    return index;
	}

	//gp.getRooms().getCurrentRoom().addItem(new Rupee(gp, temp.get(i).getX(), temp.get(i).getY()));
				
	/**
	 * Checks if the entity is hitting any object and returns the index of the object.
	 *
	 * @param e The entity to check for hits.
	 * @param p Indicates if the check is performed by the player.
	 * @return The index of the object that the entity is hitting, or -1 if no hit is detected.
	 *
	 * This method checks if the entity's solid area intersects with any of the objects in the current room.
	 * If a hit is detected, the collision state of the entity is updated based on the object's collision property.
	 * If the check is performed by the player (p == true), the index of the object is returned.
	 * The method also resets the positions of the entity and the object after the collision check.
	 */
	public int checkObject(Entity e, boolean p) {
	    int index = -1;
	    ArrayList<ItemEntity> items = gp.getRooms().getCurrentRoom().getItems();

	    for (int i = 0; i < items.size(); i++) {
	        ItemEntity item = items.get(i);
	        if (item != null) {
	            // Get the entity's position
	            e.getSolidArea().x = e.getX() + e.getSolidArea().x;
	            e.getSolidArea().y = e.getY() + e.getSolidArea().y;

	            // Get the item's position
	            item.getSolidAreaI().x = item.getWorldX() + item.getSolidAreaI().x;
	            item.getSolidAreaI().y = item.getWorldY() + item.getSolidAreaI().y;

	            // Check for collision based on entity's direction
	            switch (e.getDirection()) {
	                case "up":
	                    e.getSolidArea().y -= e.getSpeed();
	                    if (e.getSolidArea().intersects(item.getSolidAreaI())) {
	                        if (item.isCollision()) {
	                            e.setCollisionOn(true);
	                        }
	                        if (p) {
	                            index = i;
	                        }
	                    }
	                    break;
	                case "down":
	                    e.getSolidArea().y += e.getSpeed();
	                    if (e.getSolidArea().intersects(item.getSolidAreaI())) {
	                        if (item.isCollision()) {
	                            e.setCollisionOn(true);
	                        }
	                        if (p) {
	                            index = i;
	                        }
	                    }
	                    break;
	                case "left":
	                    e.getSolidArea().x -= e.getSpeed();
	                    if (e.getSolidArea().intersects(item.getSolidAreaI())) {
	                        if (item.isCollision()) {
	                            e.setCollisionOn(true);
	                        }
	                        if (p) {
	                            index = i;
	                        }
	                    }
	                    break;
	                case "right":
	                    e.getSolidArea().x += e.getSpeed();
	                    if (e.getSolidArea().intersects(item.getSolidAreaI())) {
	                        if (item.isCollision()) {
	                            e.setCollisionOn(true);
	                        }
	                        if (p) {
	                            index = i;
	                        }
	                    }
	                    break;
	            }

	            e.getSolidArea().x = e.getSolidAreaDefX();
	            e.getSolidArea().y = e.getSolidAreaDefY();
	            item.getSolidAreaI().x = item.getSolidAreaIDefX();
	            item.getSolidAreaI().y = item.getSolidAreaIDefY();
	        }
	    }

	    gp.setItems(items);
	    return index;
	}

}

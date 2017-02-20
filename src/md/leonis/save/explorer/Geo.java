package md.leonis.save.explorer;

import com.google.gson.Gson;

import java.io.File;

public class Geo implements Comparable<Geo> {

    private Planets planet;
    private String name;

    // 0x400: 00 - X, but inverse. In SS always 00. Probably offset in tilemap
    private int x;        // 0x401-0x402  X coordinate on Map. Step on right: 0006 → 1006 → 2006. 401 max B0
    // 0x403: 00
    // 0x404: 00 - Y, but inverse. In SS always 00. Probably offset in tilemap
    private int y;        // 0x405-0x406  Y coordinate on Map. Examples: 0001, 5001. 405 max B0
    // 0x407: 00
    private int map;    // 0x408-0x409  Map #. Examples: 00 00 — Palma, 04 04 — Camineet
    // map: 00 00
    // 00 = Palma
    // 01 = Motavia
    // 02 = Dezoris
    // 04 = Camineet, Parolit
    // 05 = Gothic, Eppi, Loar, Abion
    // 06 = Drasgow, Shion
    // 07 = Paseo, Uzo, Casba, Sopia
    // 08 = Skure, Twintown (entrances)
    // 09 = Skure, Twintown
    // 0A = Air Castle
    // Second number - # of map (0x00 - 0x17)


    private int direction;// 0x40A        Direction in dungeon. Default = 0; To right: 1 → 2 → 3. Contains after exit from dungeon
    // 0x40B: 00
    private int room;   // 0x40C        Room # in dungeon; Both X (4-bit), Y (4-bit). Examples: 5E
    private int dungeon;// 0x40D        Dungeon #. Examples: Medusa's Cave, outdoor: 00; Camineet Warehouse: 02
    // (00-3A) Interesting things: some dungeons intersects (Scion/Naula caves, ...)
    private int transport;// 0x40E        00 -> 08 — hovercraft, 04 — landrover, 0C - ice digger
    private int animation1;// 0x40F        00..03 - transport animation? set 00
    private int animation2;// 0x410        00..03 - transport animation? set 00
    private int y2;       // 0x411-0x412  Y coordinate on Map. Same as 0x405-0x406
    private int x2;       // 0x413-0x414  X coordinate on Map. Same as 0x401-0x402
    private int color;    // 0x415        Dungeon color. (00-0A) Examples: 02: light green, 03: blue? 04: blue, 05: light blue, 06: yellow, 07: pink,...
    private int type;     // 0x416        Type of environment. 0D: outdoor, cities; 0B: dungeons
    private int church;   // 0x417        Church # (for teleport); Examples: 00: no; 01: Camineet, 02: Gothic, 03: Loar, ...
    // 0x418-4FF: 00


    public Geo(File map) {
        String fileName = map.getName().replace(".sav", "");
        String[] chunks = fileName.split(" - ");
        this.name = chunks[1];
        chunks = chunks[0].split("-");
        this.x = Integer.parseInt(chunks[0].substring(1), 16);
        this.y = Integer.parseInt(chunks[1].substring(1), 16);
        this.map = Integer.parseInt(chunks[2].substring(1), 16);
        this.direction = Integer.parseInt(chunks[3].substring(1), 16);
        this.room = Integer.parseInt(chunks[5].substring(1), 16);
        this.dungeon = Integer.parseInt(chunks[6].substring(1), 16);
        this.color = Integer.parseInt(chunks[7].substring(1, 3), 16);
        this.type = Integer.parseInt(chunks[7].substring(3, 5), 16);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toCSV() {
        return String.format("\"%04X\"", x) + ";" +
                String.format("\"%04X\"", y) + ";" +
                String.format("\"%04X\"", map) + ";" +
                String.format("\"%02X\"", dungeon) + ";" +
                String.format("\"%02X\"", room) + ";" +
                String.format("\"%02X\"", direction) + ";" +
                String.format("\"%02X\"", color) + ";" +
                String.format("\"%02X\"", type) + ";" +
                name;
    }


    public Planets getPlanet() {
        return planet;
    }

    public void setPlanet(Planets planet) {
        this.planet = planet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getDungeon() {
        return dungeon;
    }

    public void setDungeon(int dungeon) {
        this.dungeon = dungeon;
    }

    public int getTransport() {
        return transport;
    }

    public void setTransport(int transport) {
        this.transport = transport;
    }

    public int getAnimation1() {
        return animation1;
    }

    public void setAnimation1(int animation1) {
        this.animation1 = animation1;
    }

    public int getAnimation2() {
        return animation2;
    }

    public void setAnimation2(int animation2) {
        this.animation2 = animation2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getChurch() {
        return church;
    }

    public void setChurch(int church) {
        this.church = church;
    }

    @Override
    public String toString() {
        return "Geo{" +
                "planet=" + planet +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", map=" + map +
                ", direction=" + direction +
                ", room=" + room +
                ", dungeon=" + dungeon +
                ", transport=" + transport +
                ", animation1=" + animation1 +
                ", animation2=" + animation2 +
                ", y2=" + y2 +
                ", x2=" + x2 +
                ", color=" + color +
                ", type=" + type +
                ", church=" + church +
                '}';
    }

    @Override
    public int compareTo(Geo o) {
        int result = Integer.valueOf(map).compareTo(o.getMap());
        if (result != 0) return result;
        result = Integer.valueOf(dungeon).compareTo(o.getDungeon());
        if (result != 0) return result;
        return Integer.valueOf(room).compareTo(o.getRoom());
    }
}

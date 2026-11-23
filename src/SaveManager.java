import java.io.*;
import java.nio.file.*;
import java.util.Properties;

/** Handles persistence of A Última Relíquia's gameplay state. */
public final class SaveManager {
    private static final Path SAVE_PATH = Paths.get("A_Ultima_Reliquia_Save", "savegame.properties");
    private SaveManager() {}

    public static final class SaveData {
        public int phase = 1, playerX = 90, playerY = 456, health = 3;
        public int[] keyCollected = new int[0];
        public int[] enemyHealth = new int[0];
        public int bossHealth = -1;
        public boolean relicCollected;
    }

    public static Path getSavePath() { return SAVE_PATH.toAbsolutePath(); }
    public static boolean saveExists() { return Files.isRegularFile(SAVE_PATH); }

    public static void save(SaveData data) throws IOException {
        if (data == null) throw new IOException("Não há dados de partida para salvar.");
        Properties p = new Properties();
        p.setProperty("version", "1");
        p.setProperty("phase", Integer.toString(data.phase));
        p.setProperty("playerX", Integer.toString(data.playerX));
        p.setProperty("playerY", Integer.toString(data.playerY));
        p.setProperty("health", Integer.toString(data.health));
        p.setProperty("keys", encode(data.keyCollected));
        p.setProperty("enemies", encode(data.enemyHealth));
        p.setProperty("bossHealth", Integer.toString(data.bossHealth));
        p.setProperty("relicCollected", Boolean.toString(data.relicCollected));
        Path parent = SAVE_PATH.getParent();
        Files.createDirectories(parent);
        Path temp = parent.resolve("savegame.tmp");
        try (OutputStream out = Files.newOutputStream(temp)) {
            p.store(out, "A Última Relíquia - Trabalho 2 save");
        }
        try {
            Files.move(temp, SAVE_PATH, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (AtomicMoveNotSupportedException ex) {
            Files.move(temp, SAVE_PATH, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static SaveData load() throws IOException {
        if (!saveExists()) throw new FileNotFoundException("Não existe partida salva.");
        Properties p = new Properties();
        try (InputStream in = Files.newInputStream(SAVE_PATH)) { p.load(in); }
        try {
            if (!"1".equals(p.getProperty("version"))) throw new IllegalArgumentException("Versão de save incompatível.");
            SaveData d = new SaveData();
            d.phase = Integer.parseInt(p.getProperty("phase"));
            d.playerX = Integer.parseInt(p.getProperty("playerX"));
            d.playerY = Integer.parseInt(p.getProperty("playerY"));
            d.health = Integer.parseInt(p.getProperty("health"));
            d.keyCollected = decode(p.getProperty("keys", ""));
            d.enemyHealth = decode(p.getProperty("enemies", ""));
            d.bossHealth = Integer.parseInt(p.getProperty("bossHealth", "-1"));
            d.relicCollected = Boolean.parseBoolean(p.getProperty("relicCollected", "false"));
            if (d.phase < 1 || d.phase > 3 || d.health < 0 || d.health > 3 || d.playerX < 0 || d.playerX > 2950 || d.playerY < -1000 || d.playerY > 1000)
                throw new IllegalArgumentException("Dados de save fora dos limites.");
            return d;
        } catch (RuntimeException ex) {
            throw new IOException("O arquivo de salvamento está inválido ou corrompido.", ex);
        }
    }

    public static void deleteSave() throws IOException { Files.deleteIfExists(SAVE_PATH); }
    private static String encode(int[] values) {
        if (values == null || values.length == 0) return "";
        StringBuilder b = new StringBuilder();
        for (int i=0;i<values.length;i++) { if (i>0)b.append(','); b.append(values[i]); }
        return b.toString();
    }
    private static int[] decode(String s) {
        if (s == null || s.trim().isEmpty()) return new int[0];
        String[] parts=s.split(","); int[] a=new int[parts.length];
        for(int i=0;i<parts.length;i++)a[i]=Integer.parseInt(parts[i].trim());
        return a;
    }
}

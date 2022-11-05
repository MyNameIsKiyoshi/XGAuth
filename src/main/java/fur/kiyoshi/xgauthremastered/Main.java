package fur.kiyoshi.xgauthremastered;

import fur.kiyoshi.xgauthremastered.events.*;
import fur.kiyoshi.xgauthremastered.utils.Format;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public final class Main extends JavaPlugin {

    public static ArrayList<UUID> authlocked = new ArrayList<>();

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public void Initialize() {
        instance = this;
        Bukkit.getLogger().info(Format.color("[XGAuth] Successfully Enabled By MyNameIsKiyoshi Version <30.20a>"));
        int pluginId = 16806;
        Metrics metrics = new Metrics(this, pluginId);
        metrics.addCustomChart(new SimplePie("runs_viaversion",
                () -> Bukkit.getPluginManager().isPluginEnabled("AuthMe") ? "Yes" : "No"));
        Bukkit.getLogger().info(Format.color("[XGAuth] Loaded Metrics Optionals"));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void Config(){
        getConfig().options().parseComments(true);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        File lang = new File(this.getDataFolder(), "lang");
        File language = new File(this.getDataFolder() + "/lang/language.yml");
        if (!lang.exists()) {
            lang.mkdir();
        }
        FileConfiguration languageconfig = YamlConfiguration.loadConfiguration(language);
        if (!language.exists()) {
            try {
                language.createNewFile();
                languageconfig.createSection("Messages");
                languageconfig.set("Messages.Prefix", Format.hex("#274d64[XGAuth] "));
                languageconfig.set("Messages.AuthSuccess", Format.hex("#274d64 {player} Successfully Authenticated."));
                languageconfig.set("Messages.WrongCode", Format.hex("#ff4955 {player} Wrong Authentication Code Or Expired."));
                languageconfig.set("Messages.AuthBefore", Format.hex("#ff4955 Before Run Any Command Authenticate YourSelf First."));
                languageconfig.set("Messages.CodeGenerated", Format.hex("#274d64 {player} Your Authentication Code Is ({code}) You Can Copy The Code By Hovering Over It And Clicking On It"));
                languageconfig.set("Messages.WorongCodeStaffNotification", Format.hex("#ff4955 [STAFF] {player} Wrong Authentication Code"));
                languageconfig.set("Messages.AuthStaffNotification", Format.hex("#274d64 [STAFF] {player} Has Successfully Authenticated"));
                languageconfig.set("Messages.CodeGeneratedInstruction", Format.hex("#274d64 Open Your Google Authentication App Downloadable From PlayStore Or AppStore, Or Use The Web Version For PC"));
                languageconfig.set("Messages.AuthRequired", Format.hex("#274d64 Open Your Google Authentication App And Authenticate YourSelf."));
                languageconfig.set("Messages.MessageHoverCode", Format.hex("#274d64 > Click Me To Copy The Code <"));
                languageconfig.save(language);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void Events(){
        getServer().getPluginManager().registerEvents(new BreakEvent(), this);
        getServer().getPluginManager().registerEvents(new CheckEvent(), this);
        getServer().getPluginManager().registerEvents(new CMDEvent(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new MoveEvent(), this);
        getServer().getPluginManager().registerEvents(new PlaceEvent(), this);
        getServer().getPluginManager().registerEvents(new QuitEvent(), this);
    }

    @Override
    public void onEnable() {
        Initialize();
        Config();
        Events();
    }
}

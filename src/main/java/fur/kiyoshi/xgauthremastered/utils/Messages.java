package fur.kiyoshi.xgauthremastered.utils;

import fur.kiyoshi.xgauthremastered.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

@SuppressWarnings("all")
public class Messages {
    public static File language = new File(Main.getInstance().getDataFolder() + "/lang/language.yml");
    public static FileConfiguration languageconfiguration;

    public static String getPrefix(){
        return Format.color(languageconfiguration.getString("Messages.Prefix"));
    }

    public static String authSuccess(){
        return getPrefix() + Format.color(languageconfiguration.getString("Messages.AuthSuccess"));
    }

    public static String wrongCode(){
        return getPrefix() + Format.color(languageconfiguration.getString("Messages.WrongCode"));
    }

    public static String authBefore(){
        return getPrefix() + Format.color(languageconfiguration.getString("Messages.AuthBefore"));
    }

    public static String codeGenerated(){
        return getPrefix() + Format.color(languageconfiguration.getString("Messages.CodeGenerated"));
    }

    public static String wrongCodeStaffNotification(){
        return getPrefix() + Format.color(languageconfiguration.getString("Messages.WorongCodeStaffNotification"));
    }

    public static String authSuccessStaffNotification(){
        return getPrefix() + Format.color(languageconfiguration.getString("Messages.AuthStaffNotification"));
    }

    public static String codeGeneratedInstructions(){
        return getPrefix() + Format.color(languageconfiguration.getString("Messages.CodeGeneratedInstruction"));
    }

    public static String authRequired(){
        return getPrefix() + Format.color(languageconfiguration.getString("Messages.AuthRequired"));
    }

    public static String messageHoverCode(){
        return getPrefix() + Format.color(languageconfiguration.getString("Messages.MessageHoverCode"));
    }

    static {
        languageconfiguration = YamlConfiguration.loadConfiguration(language);
    }
}

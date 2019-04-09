package StuffTheSpire;

import GifTheSpire.util.GifAnimation;
import StuffTheSpire.cards.*;
import StuffTheSpire.relics.*;
import StuffTheSpire.util.IDCheckDontTouchPls;
import StuffTheSpire.util.TextureLoader;
import StuffTheSpire.variables.DiminishingVariable;
import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


@SpireInitializer
public class StuffTheSpireMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        PostInitializeSubscriber {
    public static final Logger logger = LogManager.getLogger(StuffTheSpireMod.class.getName());
    private static String modID;
    private static GifAnimation Plasmapunch = new GifAnimation("StuffTheSpireResources/images/animations/plasmapunchsheet.png", 11, 1, 0,0,0,0,false);
    private static GifAnimation Plasmapulse = new GifAnimation("StuffTheSpireResources/images/animations/plasmapulsesheet.png", 11, 1, 0,0,0,0,false);
    private static final String MODNAME = "Stuff the Spire";
    private static final String AUTHOR = "Lobbienjonsji"; // And pretty soon - You!
    private static final String DESCRIPTION = "Adds new cards, relics, events and other stuff with full GifTheSpireLib support";
    public static final String BADGE_IMAGE = "StuffTheSpireResources/images/Badge.png";
    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }
    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makeOrbPath(String resourcePath) {
        return getModID() + "Resources/orbs/" + resourcePath;
    }
    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }
    public static String makeAnimPath(String resourcePath) {
        return getModID() + "Resources/images/animations/" + resourcePath;
    }
    public StuffTheSpireMod() {
        logger.info("Subscribe to BaseMod hooks");

        BaseMod.subscribe(this);
        setModID("StuffTheSpire");
            }

    // ====== NO EDIT AREA ======
    // DON'T TOUCH THIS STUFF. IT IS HERE FOR STANDARDIZATION BETWEEN MODS AND TO ENSURE GOOD CODE PRACTICES.
    // IF YOU MODIFY THIS I WILL HUNT YOU DOWN AND DOWNVOTE YOUR MOD ON WORKSHOP
    //I can confirm I read this

    public static void setModID(String ID) { // DON'T EDIT - I won't
        Gson coolG = new Gson(); // EY DON'T EDIT THIS - I said I won't
        //   String IDjson = Gdx.files.internal("IDCheckStrings.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i hate u Gdx.files - Ey Gdx has feelings too!
        InputStream in = StuffTheSpireMod.class.getResourceAsStream("/IDCheckStrings.json"); // DON'T EDIT THIS ETHER - I said I WON'T
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // OR THIS, DON'T EDIT IT - I WON'T EDIT THIS
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) { // DO *NOT* CHANGE THIS ESPECIALLY, TO EDIT YOUR MOD ID, SCROLL UP JUST A LITTLE, IT'S JUST ABOVE - I ALREADY EDITED MY MOD ID
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION); // THIS ALSO DON'T EDIT - HOW MANY TIMES DO I HAVE TO TELL YOU I WON'T!?!?!?
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) { // NO - I KNOW!!!
            modID = EXCEPTION_STRINGS.DEFAULTID; // DON'T - I WON'T!!!
        } else { // NO EDIT AREA - STOP SCREAMING AT ME!!!
            modID = ID; // DON'T WRITE OR CHANGE THINGS HERE NOT EVEN A LITTLE - SHUT UP!
        } // NO - YES!
    } // NO - OH YES!

    public static String getModID() { // NO
        return modID; // DOUBLE NO
    } // NU-UH - WELL, TRIPLE YES THEN!

    private static void pathCheck() { // ALSO NO - ALSO YES!
        Gson coolG = new Gson(); // NNOPE DON'T EDIT THIS - YOU ARE REALLY GETTING ANNOYING!
        //   String IDjson = Gdx.files.internal("IDCheckStrings.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i still hate u btw Gdx.files - And Gdx still has feelings
        InputStream in = StuffTheSpireMod.class.getResourceAsStream("/IDCheckStrings.json"); // DON'T EDIT THISSSSS - YESSS SSSSIR!
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // NAH, NO EDIT - NAH NO SHOUTING
        String packageName = StuffTheSpireMod.class.getPackage().getName(); // STILL NOT EDIT ZONE - ALSO: NO SHOUTING ZONE!
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources"); // PLEASE DON'T EDIT THINGS HERE, THANKS - YOU ARE VERY WELCOME!
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) { // LEAVE THIS EDIT-LESS - EDIT OwO
            if (!packageName.equals(getModID())) { // NOT HERE ETHER - ANOTHER EDIT ÖwÖ
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID()); // THIS IS A NO-NO - STOP! JUST STOP! PLEASE!
            } // WHY WOULD U EDIT THIS - BECAUSE I CAN!!!
            if (!resourcePathExists.exists()) { // DON'T CHANGE THIS - FOR THE LAST TIME: I WON'T
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources"); // NOT THIS - NO U!
            }// NO - YES
        }// NO - YES
    }// NO - *Stabs Grem*
    // ====== YOU CAN EDIT AGAIN ======


    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("Initializing StuffTheSpireMod ö/");
        StuffTheSpireMod StuffTheSpire = new StuffTheSpireMod();
        logger.info("Wer das ließt ist doof");
    }


    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);

        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addUIElement(new ModLabel("StuffTheSpireMod doesn't have any settings! An example of those may come later.", 400.0f, 700.0f,
                settingsPanel, (me) -> {
        }));
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        logger.info("Done loading badge Image and mod options");
        Plasmapunch.create();
        Plasmapunch.addAsCardAnimation("StuffTheSpire:PlasmaPunch");
        Plasmapunch.setAnimationspeed(0.05F);
        Plasmapulse.create();
        Plasmapulse.addAsCardAnimation("StuffTheSpire:PlasmaPulse");
        Plasmapulse.setAnimationspeed(0.05F);
    }
    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");
        logger.info("Done editing potions");
    }

    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");
        RelicLibrary.addGreen(new WitherSkull());
        RelicLibrary.addRed(new DullRazor());
        BaseMod.addRelic(new RunicHypercube(), RelicType.SHARED);
        BaseMod.addRelic(new NeowsTribute(), RelicType.SHARED);
        BaseMod.addRelic(new Mouthbags(), RelicType.SHARED);
        BaseMod.addRelic(new Coconut(), RelicType.SHARED);
        BaseMod.addRelic(new Expresso(), RelicType.SHARED);
        BaseMod.addRelic(new PointBlank(), RelicType.SHARED);
        BaseMod.addRelic(new Nightcore(), RelicType.SHARED);
        BaseMod.addRelic(new PaperKangaroo(), RelicType.SHARED);
        BaseMod.addRelic(new Wildfire(), RelicType.SHARED);
        BaseMod.addRelic(new GrapeVine(), RelicType.SHARED);
        BaseMod.addRelic(new HeavyRock(), RelicType.SHARED);
        BaseMod.addRelic(new HighNoonTea(), RelicType.SHARED);
        BaseMod.addRelic(new HistoryBook(), RelicType.SHARED);
        BaseMod.addRelic(new ForceGauntlet(), RelicType.SHARED);
        BaseMod.addRelic(new RunicConfusion(), RelicType.SHARED);
        //BaseMod.addRelic(new LittleGreenCactus(), RelicType.SHARED); NOPE!!!
        logger.info("Done adding relics!");
    }

    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");
        pathCheck();
        logger.info("Add variable");
        BaseMod.addDynamicVariable(new DiminishingVariable());
        logger.info("Adding cards");
        BaseMod.addCard(new PlasmaPunch());
        BaseMod.addCard(new FreeRealEstate());
        BaseMod.addCard(new PlasmaPulse());
        BaseMod.addCard(new BloodClot());
        BaseMod.addCard(new TitanForm());
        BaseMod.addCard(new Recharge());
        BaseMod.addCard(new ReptileScales());
        BaseMod.addCard(new Shiver());
        BaseMod.addCard(new LaunchingStrike());
        BaseMod.addCard(new Synthwave());
        BaseMod.addCard(new ForEach());
        BaseMod.addCard(new Pyroclast());
        BaseMod.addCard(new Discharge());
        BaseMod.addCard(new LoadUp());
        logger.info("Making sure the cards are unlocked.");
        UnlockTracker.unlockCard(PlasmaPunch.ID);
        UnlockTracker.unlockCard(PlasmaPulse.ID);
        logger.info("Done adding cards!");
    }
    @Override
    public void receiveEditStrings() {
        logger.info("Beginning to edit strings");
        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/localization/eng/StuffTheSpireMod-Card-Strings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/localization/eng/StuffTheSpireMod-Power-Strings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/localization/eng/StuffTheSpireMod-Relic-Strings.json");
        BaseMod.loadCustomStringsFile(EventStrings.class,
                getModID() + "Resources/localization/eng/StuffTheSpireMod-Event-Strings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                getModID() + "Resources/localization/eng/StuffTheSpireMod-Potion-Strings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                getModID() + "Resources/localization/eng/StuffTheSpireMod-Character-Strings.json");
        BaseMod.loadCustomStringsFile(OrbStrings.class,
                getModID() + "Resources/localization/eng/StuffTheSpireMod-Orb-Strings.json");
        logger.info("Done edittting strings");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/StuffTheSpireMod-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

}

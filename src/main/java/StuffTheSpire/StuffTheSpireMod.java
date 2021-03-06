package StuffTheSpire;

import GifTheSpire.util.GifAnimation;
import StuffTheSpire.cards.blessings.*;
import StuffTheSpire.cards.blue.*;
import StuffTheSpire.cards.colorless.*;
import StuffTheSpire.cards.curses.CuriosityCurse;
import StuffTheSpire.cards.curses.GnomedCurse;
import StuffTheSpire.cards.curses.WarCurse;
import StuffTheSpire.cards.green.*;
import StuffTheSpire.cards.red.*;
import StuffTheSpire.cards.status.FlameBurst;
import StuffTheSpire.events.*;
import StuffTheSpire.relics.*;
import StuffTheSpire.util.IDCheckDontTouchPls;
import StuffTheSpire.util.TextureLoader;
import StuffTheSpire.variables.DiminishingVariable;
import archetypeAPI.archetypes.AbstractArchetype;
import archetypeAPI.cards.ArchetypeSelectCard;
import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static archetypeAPI.ArchetypeAPI.loadArchetypes;


@SpireInitializer
public class StuffTheSpireMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        PostInitializeSubscriber,
        PreStartGameSubscriber,
        PostDeathSubscriber,
        AddAudioSubscriber {
    public static final Logger logger = LogManager.getLogger(StuffTheSpireMod.class.getName());
    private static String modID;
    public static boolean HasArchetypeAPI;
    public static boolean HasArchetypeRelic = false;
    public static CardGroup CommonChainArchetype;
    public static CardGroup UncommonChainArchetype;
    public static CardGroup RareChainArchetype;
    public static CardGroup ShivArchetype;
    public static CardGroup LightningArchetype;
    public static CardGroup StrikeArchetype;
    public static CardGroup DiscardArchetype;
    public static CardGroup FrostArchetype;
    public static CardGroup HorsemanBlessings;
    public static final Color DEFAULT_GRAY = CardHelper.getColor(64.0f, 70.0f, 70.0f);
    private static GifAnimation Plasmapunch = new GifAnimation("StuffTheSpireResources/images/animations/plasmapunchsheet.png", 11, 1, 0,0,0,0,false);
    private static GifAnimation Plasmapulse = new GifAnimation("StuffTheSpireResources/images/animations/plasmapulsesheet.png", 11, 1, 0,0,0,0,false);
    public static GifAnimation Dark_Fade = new GifAnimation("StuffTheSpireResources/images/animations/DarkFadeBg.png", 7, 7,0,0,0,0, true);
    public static GifAnimation Unleash = new GifAnimation("StuffTheSpireResources/images/animations/unleashsheet.png", 4, 1, 0, 0, 0, 0, false);
    private static final String MODNAME = "Stuff the Spire";
    private static final String AUTHOR = "Lobbienjonsji"; // And pretty soon - You!
    private static final String DESCRIPTION = "Adds new cards, relics, events and other stuff with full GifTheSpireLib support";
    public static final String BADGE_IMAGE = "StuffTheSpireResources/images/Badge.png";
    private static final String ATTACK_DEFAULT_GRAY = "StuffTheSpireResources/images/512/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_GRAY = "StuffTheSpireResources/images/512/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_GRAY = "StuffTheSpireResources/images/512/bg_power_default_gray.png";

    private static final String ENERGY_ORB_DEFAULT_GRAY = "StuffTheSpireResources/images/512/card_default_gray_orb.png";
    private static final String CARD_ENERGY_ORB = "StuffTheSpireResources/images/512/card_small_orb.png";
    private static final String ATTACK_DEFAULT_GRAY_PORTRAIT = "StuffTheSpireResources/images/1024/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_GRAY_PORTRAIT = "StuffTheSpireResources/images/1024/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_GRAY_PORTRAIT = "StuffTheSpireResources/images/1024/bg_power_default_gray.png";
    private static final String ENERGY_ORB_DEFAULT_GRAY_PORTRAIT = "StuffTheSpireResources/images/1024/card_default_gray_orb.png";
    public static TextureAtlas.AtlasRegion Frame;
    public static Texture BlessingBg;
    public static Texture BigFrame;
    public static CardGroup Blessings;
    public static TextureAtlas BlessingBgAtlas;
    public static TextureAtlas.AtlasRegion BlessingBgRegion;

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
        BaseMod.addColor(StuffTheSpire.patches.cards.CardColors.BLESSING, DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY,
                DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY,
                ATTACK_DEFAULT_GRAY, SKILL_DEFAULT_GRAY, POWER_DEFAULT_GRAY, ENERGY_ORB_DEFAULT_GRAY,
                ATTACK_DEFAULT_GRAY_PORTRAIT, SKILL_DEFAULT_GRAY_PORTRAIT, POWER_DEFAULT_GRAY_PORTRAIT,
                ENERGY_ORB_DEFAULT_GRAY_PORTRAIT, CARD_ENERGY_ORB);
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

    public static AbstractCard GetRandomArchetype(CardGroup c) {
        AbstractCard.CardRarity rarity = AbstractDungeon.rollRarity();
        switch (rarity) {
            case RARE:
                return c.getRandomCard(true, AbstractCard.CardRarity.RARE);
            case UNCOMMON:
                return c.getRandomCard(true, AbstractCard.CardRarity.UNCOMMON);
            case COMMON:
                return c.getRandomCard(true, AbstractCard.CardRarity.COMMON);
            default:
                return c.getRandomCard(true, AbstractCard.CardRarity.COMMON);
        }
    }

    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");
        logger.info("Done editing potions");
    }

    public static AbstractCard GetRandomChainArchetype() {
        AbstractCard.CardRarity rarity = AbstractDungeon.rollRarity();
        switch (rarity) {
            case RARE:
                return RareChainArchetype.getRandomCard(true);
            case UNCOMMON:
                return UncommonChainArchetype.getRandomCard(true);
            case COMMON:
                return CommonChainArchetype.getRandomCard(true);
            default:
                return CommonChainArchetype.getRandomCard(true);
        }
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
        BlessingBg = TextureLoader.getTexture("StuffTheSpireResources/images/512/bg_skill_default_gray.png");
        BlessingBgAtlas = new TextureAtlas();
        BlessingBgAtlas.addRegion("BlessBg", BlessingBg, 0, 0, BlessingBg.getWidth(), BlessingBg.getHeight());
        BlessingBgRegion = BlessingBgAtlas.findRegion("BlessBg");
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        logger.info("Done loading badge Image and mod options");
        Plasmapunch.create();
        Plasmapunch.addAsCardAnimation("StuffTheSpire:PlasmaPunch");
        Plasmapunch.setAnimationspeed(0.05F);
        Plasmapulse.create();
        Plasmapulse.addAsCardAnimation("StuffTheSpire:PlasmaPulse");
        Plasmapulse.setAnimationspeed(0.05F);
        Dark_Fade.create();
        Dark_Fade.setAnimationspeed(0.05F);
        Dark_Fade.addAsBackgroundAnimation();
        Unleash.create();
        Unleash.addAsCardAnimation("StuffTheSpire:Unleash");
        Unleash.setAnimationspeed(0.25F);
        BaseMod.addEvent(TheShadows.ID, TheShadows.class, TheCity.ID);
        BaseMod.addEvent(NeowistPriest.ID, NeowistPriest.class, TheCity.ID);
        BaseMod.addEvent(FlowerFields.ID, FlowerFields.class, Exordium.ID);
        BaseMod.addEvent(Horseman.ID, Horseman.class, TheCity.ID);
        BaseMod.addEvent(Wunderkammer.ID, Wunderkammer.class);
        BaseMod.addEvent(GreaterSnecko.ID, GreaterSnecko.class, TheBeyond.ID);
        CommonChainArchetype = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        UncommonChainArchetype = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        RareChainArchetype = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        CommonChainArchetype.addToTop(new ChainStrike());
        CommonChainArchetype.addToTop(new Razor());
        UncommonChainArchetype.addToTop(new Flow());
        UncommonChainArchetype.addToTop(new Unleash());
        UncommonChainArchetype.addToTop(new AbyssLink());
        RareChainArchetype.addToTop(new Surge());
        RareChainArchetype.addToTop(new Flurry());
        ShivArchetype = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        LightningArchetype = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        StrikeArchetype = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        DiscardArchetype = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        FrostArchetype = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        Blessings = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        HorsemanBlessings = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        Blessings.addToTop(new BrainWave());
        Blessings.addToTop(new CelestialWarden());
        Blessings.addToTop(new Harmony());
        Blessings.addToTop(new Rapture());
        Blessings.addToTop(new Vigor());
        Blessings.addToTop(new Ambrosia());
        Blessings.addToTop(new Innocence());
        Blessings.addToTop(new Brawn());
        HorsemanBlessings.addToTop(new WarGroove());
        HorsemanBlessings.addToTop(new CopiaOfFamine());
        HorsemanBlessings.addToTop(new DeathNote());
        HorsemanBlessings.addToTop(new PestControl());
        if (Loader.isModLoaded("archetypeapi")) {
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-Shiv-Archetype-Addon.json");
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-Chain-Archetype.json");
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-Discard-Archetype-Addon.json");
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-Poison-Archetype-Addon.json");
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-Silent-Block-Archetype-Addon.json");
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-Silent-Basic-Archetype-Addon.json");
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-TwoCost-Archetype.json");
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-Exhaust-Archetype-Addon.json");
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-Ironclad-Block-Archetype-Addon.json");
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-Self-Damage-Archetype-Addon.json");
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-Strength-Archetype-Addon.json");
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-Ironclad-Basic-Archetype-Addon.json");
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-Dark-Archetype-Addon.json");
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-Defect-Basic-Archetype-Addon.json");
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-Frost-Archetype-Addon.json");
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-Orb-Support-Archetype-Addon.json");
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-Plasma-Archetype-Addon.json");
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-Lightning-Archetype-Addon.json");
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-Claw-Archetype-Addon.json");
            loadArchetypes(getModID() + "Resources/localization/eng/StuffTheSpireMod-Power-Archetype-Addon.json");
        }
        Frame = ImageMaster.CARD_FRAME_SKILL_COMMON;
        BigFrame = ImageMaster.CARD_FRAME_SKILL_COMMON_L.getTexture();
    }

    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");
        RelicLibrary.addGreen(new WitherSkull());
        RelicLibrary.addRed(new DullRazor());
        RelicLibrary.addBlue(new ColdMirror());
        RelicLibrary.addRed(new BottledHeartBeat());
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
        BaseMod.addRelic(new EmeraldSignet(), RelicType.SHARED);
        BaseMod.addRelic(new RubySignet(), RelicType.SHARED);
        BaseMod.addRelic(new SapphireSignet(), RelicType.SHARED);
        BaseMod.addRelic(new MayFlowers(), RelicType.SHARED);
        BaseMod.addRelic(new BlackoutCurse(), RelicType.SHARED);
        BaseMod.addRelic(new Gunfinga(), RelicType.SHARED);
        BaseMod.addRelic(new ChainArchetype(), RelicType.SHARED);
        BaseMod.addRelic(new DarkSteelAnvil(), RelicType.SHARED);
        BaseMod.addRelic(new OldFever(), RelicType.SHARED);
        BaseMod.addRelic(new Rhapsscallions(), RelicType.SHARED);
        BaseMod.addRelic(new ShivArchetype(), RelicType.SHARED);
        BaseMod.addRelic(new LightningArchetype(), RelicType.SHARED);
        BaseMod.addRelic(new StrikeArchetype(), RelicType.SHARED);
        BaseMod.addRelic(new DiscardArchetype(), RelicType.SHARED);
        BaseMod.addRelic(new FrostArchetype(), RelicType.SHARED);
        BaseMod.addRelic(new BottledEssence(), RelicType.SHARED);
        BaseMod.addRelic(new RedRose(), RelicType.SHARED);
        BaseMod.addRelic(new OccultApparatus(), RelicType.SHARED);
        BaseMod.addRelic(new GreaterSneckoEye(), RelicType.SHARED);
        BaseMod.addRelic(new Experiment12(), RelicType.SHARED);
        BaseMod.addRelic(new Saxophone(), RelicType.SHARED);
        BaseMod.addRelic(new Barrel(), RelicType.SHARED);
        BaseMod.addRelic(new TintedRock(), RelicType.SHARED);
        BaseMod.addRelic(new ModemDavis(), RelicType.SHARED);
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
        BaseMod.addCard(new Ambrosia());
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
        BaseMod.addCard(new Python());
        BaseMod.addCard(new RockThrow());
        BaseMod.addCard(new Injection());
        BaseMod.addCard(new FlameBurst());
        BaseMod.addCard(new Incineration());
        BaseMod.addCard(new BumpUp());
        BaseMod.addCard(new RagingBull());
        BaseMod.addCard(new SnapShoot());
        BaseMod.addCard(new Snapshot());
        BaseMod.addCard(new Deluminance());
        BaseMod.addCard(new Incursion());
        BaseMod.addCard(new FinalStruggle());
        BaseMod.addCard(new Coldhearted());
        BaseMod.addCard(new EventHorizon());
        BaseMod.addCard(new DoubleShiv());
        BaseMod.addCard(new Resist());
        BaseMod.addCard(new NuclearReactor());
        BaseMod.addCard(new Incantation());
        BaseMod.addCard(new Unleash());
        BaseMod.addCard(new ChainStrike());
        BaseMod.addCard(new Razor());
        BaseMod.addCard(new Flurry());
        BaseMod.addCard(new Surge());
        BaseMod.addCard(new Flow());
        BaseMod.addCard(new AbyssLink());
        BaseMod.addCard(new ShieldGenerator());
        BaseMod.addCard(new Fury());
        BaseMod.addCard(new BrimstoneBlast());
        BaseMod.addCard(new GlassShard());
        BaseMod.addCard(new Slingshot());
        BaseMod.addCard(new MirrorStance());
        BaseMod.addCard(new Sprint());
        BaseMod.addCard(new GnomedCurse());
        BaseMod.addCard(new ClusterCrunch());
        BaseMod.addCard(new Repulse());
        BaseMod.addCard(new Rend());
        BaseMod.addCard(new CleanHardDrive());
        BaseMod.addCard(new Purify());
        BaseMod.addCard(new Plugnplay());
        BaseMod.addCard(new Vigor());
        BaseMod.addCard(new BrainWave());
        BaseMod.addCard(new Rapture());
        BaseMod.addCard(new CelestialWarden());
        BaseMod.addCard(new Harmony());
        BaseMod.addCard(new Innocence());
        BaseMod.addCard(new Brawn());
        BaseMod.addCard(new WarCurse());
        BaseMod.addCard(new WarGroove());
        BaseMod.addCard(new PestControl());
        BaseMod.addCard(new DeathNote());
        BaseMod.addCard(new CopiaOfFamine());
        BaseMod.addCard(new CuriosityCurse());
        BaseMod.addCard(new Perfection());
        HasArchetypeAPI = Loader.isModLoaded("archetypeapi");
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
        BaseMod.loadCustomStringsFile(OrbStrings.class,
                getModID() + "Resources/localization/eng/StuffTheSpireMod-Orb-Strings.json");
        BaseMod.loadCustomStringsFile(UIStrings.class,
                getModID() + "Resources/localization/eng/StuffTheSpireMod-UI-Strings.json");
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


    @Override
    public void receivePreStartGame() {
        if (Loader.isModLoaded("archetypeapi")) {
            CardGroup SilentCards = AbstractArchetype.getArchetypeSelectCards(AbstractPlayer.PlayerClass.THE_SILENT);
            CardGroup DefectCards = AbstractArchetype.getArchetypeSelectCards(AbstractPlayer.PlayerClass.DEFECT);
            CardGroup IronCladCards = AbstractArchetype.getArchetypeSelectCards(AbstractPlayer.PlayerClass.IRONCLAD);
            for (int i = 0; i < SilentCards.size(); i++) {
                ArchetypeSelectCard B = (ArchetypeSelectCard) SilentCards.getNCardFromTop(i);
                logger.info(B.name);
                if (B.getArchetypeName().equals("Shiv")) {
                    B.archetypeEffect(ShivArchetype);
                    for (AbstractCard C : ShivArchetype.group) {
                        logger.info(C.name);
                    }
                }

                if (B.getArchetypeName().equals("Discard")) {
                    B.archetypeEffect(DiscardArchetype);
                    for (AbstractCard C : DiscardArchetype.group) {
                        logger.info(C.name);
                    }
                }
            }
            for (int i = 0; i < DefectCards.size(); i++) {
                ArchetypeSelectCard B = (ArchetypeSelectCard) DefectCards.getNCardFromTop(i);
                logger.info(B.name);
                if (!B.name.equals("Defect Orb Support Extension")) {
                    if (B.getArchetypeName().equals("Lightning")) {
                        B.archetypeEffect(LightningArchetype);
                        for (AbstractCard C : LightningArchetype.group) {
                            logger.info(C.name);
                        }
                    }
                    if (B.getArchetypeName().equals("Frost")) {
                        B.archetypeEffect(FrostArchetype);
                        for (AbstractCard C : FrostArchetype.group) {
                            logger.info(C.name);
                        }
                    }
                }
            }
            for (int i = 0; i < IronCladCards.size(); i++) {
                ArchetypeSelectCard B = (ArchetypeSelectCard) IronCladCards.getNCardFromTop(i);
                if (B.getArchetypeName().equals("Strike")) {
                    B.archetypeEffect(StrikeArchetype);
                    for (AbstractCard C : StrikeArchetype.group) {
                        logger.info(C.name);
                    }
                }
            }
        }
        HasArchetypeRelic = false;
    }

    @Override
    public void receivePostDeath() {
        Dark_Fade.ishidden = true;
    }


    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio("EINSUNDEINS", "StuffTheSpireResources/sounds/einsundeins.wav");
    }
}

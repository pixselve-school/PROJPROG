package utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

public class Music {
    // -----------------------------------Attributs----------------------------------
    // Fichier contenant la musique
    private File file;
    // AudioInputStream pour aller en parametre du Clip
    private AudioInputStream audio;
    // Clip avec lequel on va pouvoir manipuler le fichier
    public Clip clip;
    // Indique si la musique est en cours de lecture
    private boolean play;

    // ---------------------------------Constructeur---------------------------------
    /**
     * Classe permetant de jouer des musiques
     */
    public Music(String path) {
        URL url = getClass().getResource("/Musics/"+path+".wav");
        play = false;
        // Try/Catch super basique : On tente la fonction et si ca ne marche pas on
        // retourne l'erreur pour l'afficher dans la console
        try {
            file = new File(url.toURI());
        }
        catch (Exception ex) {
                System.out.println(ex);
            }

        try {
            audio = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audio);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    // ------------------------------------------------------------------------------
    /**
     * Constructeur vide utile pour les musiques aléatoires
     */
    public Music() {
        /* ... */}

    // -------------------------------Getters/Setters--------------------------------
    /**
     * Modifie le chemin d'acsès au ficher (modifie la musique)
     *
     * @param path
     */
    public void setPath(String path) {
        try {
            file = new File(path);
            audio = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ---------------------------------Fonctions------------------------------------
    /**
     * renvoie si la musique est en cour de lecture
     *
     * @return
     */
    public boolean isPLay() {
        return play;
    }

    // ------------------------------------------------------------------------------
    /**
     * Fonction pour lancer la lecture en boucle de la musique
     */
    public void play() {
        try {
            if (file != null) {
                play = true;
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // ------------------------------------------------------------------------------
    /**
     * Fonction pour stopper la lecture de la musique
     *
     * @Note : Remet la musique au début
     */
    public void stop() {
        try {
            if (file != null) {
                play = false;
                clip.stop();
                clip.setFramePosition(0);
            }
        } catch (Exception e) {
        }

    }

}

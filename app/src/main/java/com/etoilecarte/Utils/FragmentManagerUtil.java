package com.etoilecarte.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.etoilecarte.R;

/**
 * Created by mghrissi on 12/02/2017.
 */

public class FragmentManagerUtil {



    /**
     * Ajoute un seul fragment principal
     *
     * @param pFragment fragment principal
     */
    public static void addMainFragments(FragmentActivity pActivity, Fragment pFragment) {

        android.support.v4.app.FragmentManager vFragmentManager = pActivity.getSupportFragmentManager();
        //Commencement de la transaction
        FragmentTransaction vFragmentTransaction = vFragmentManager.beginTransaction();
        vFragmentTransaction.add(R.id.main, pFragment);
        vFragmentTransaction.addToBackStack(null);
        vFragmentTransaction.commitAllowingStateLoss();

    }


    /**
     * Ajoute un seul fragment principal avec TAG sans l'ajout à la stack
     *
     * @param pFragment fragment principal
     */
    public static void addMainFragmentsNoStack(FragmentActivity pActivity, Fragment pFragment, String tag) {

        android.support.v4.app.FragmentManager vFragmentManager = pActivity.getSupportFragmentManager();
        //Commencement de la transaction
        FragmentTransaction vFragmentTransaction = vFragmentManager.beginTransaction();
        vFragmentTransaction.add(R.id.main, pFragment, tag);
        vFragmentTransaction.commitAllowingStateLoss();

    }

    /**
     * Remplacer un seul fragment principal
     *
     * @param pFragment fragment principal
     */
    public static void replaceMainFragments(FragmentActivity pActivity, Fragment pFragment) {

        android.support.v4.app.FragmentManager vFragmentManager = pActivity.getSupportFragmentManager();
        //Commencement de la transaction
        FragmentTransaction vFragmentTransaction = vFragmentManager.beginTransaction();
        vFragmentTransaction.replace(R.id.content_frame, pFragment);
        vFragmentTransaction.addToBackStack(null);
        vFragmentTransaction.commitAllowingStateLoss();

    }

    /**
     * Remplacer un seul firstlevel fragment
     *
     * @param pFragment fragment principal
     */


    /**
     * Supprime un fragment
     *
     * @param pFragmentToRemove Le fragment à enlever
     */
    public static void removeFragment(FragmentActivity pActivity, Fragment pFragmentToRemove) {
        android.support.v4.app.FragmentManager vFragmentManager = pActivity.getSupportFragmentManager();

        FragmentTransaction vFragmentTransaction = vFragmentManager.beginTransaction();
        vFragmentTransaction.remove(pFragmentToRemove);
        vFragmentTransaction.addToBackStack(null);
        vFragmentTransaction.commitAllowingStateLoss();


    }

    /**
     * Supprime un fragment
     *
     * @param pFragmentToRemove Le fragment à enlever
     */
    public static void removeFragmentNoStack(FragmentActivity pActivity, Fragment pFragmentToRemove) {
        android.support.v4.app.FragmentManager vFragmentManager = pActivity.getSupportFragmentManager();

        FragmentTransaction vFragmentTransaction = vFragmentManager.beginTransaction();
        vFragmentTransaction.remove(pFragmentToRemove);
        vFragmentTransaction.commitAllowingStateLoss();


    }

}

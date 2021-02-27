package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class HistogramMatchingStyleTransfer {

    private Bitmap sourceImage, targetImage;

    private enum ColourChannel {
        RED,
        GREEN,
        BLUE,
        ALPHA
    }

    HistogramMatchingStyleTransfer(Bitmap sourceImage, Bitmap targetImage) {
        this.sourceImage = sourceImage;
        this.targetImage = targetImage;
    }

    Bitmap styleImage() {

        int width = targetImage.getWidth(), height = targetImage.getHeight();

        Bitmap styledImage = histogramMatchChannel(sourceImage, targetImage, ColourChannel.RED, 255);
        styledImage = histogramMatchChannel(styledImage, targetImage, ColourChannel.BLUE, 255);
        styledImage = histogramMatchChannel(styledImage, targetImage, ColourChannel.GREEN, 255);
        styledImage = histogramMatchChannel(styledImage, targetImage, ColourChannel.ALPHA, 255);

        return styledImage;
    }

    private static Bitmap histogramMatchChannel(Bitmap sourceImage, Bitmap targetImage, ColourChannel channel, int maxVal) {

        Bitmap matchedChannelImage = Bitmap.createBitmap(sourceImage.getWidth(), sourceImage.getHeight(), Bitmap.Config.ARGB_8888); // this creates a MUTABLE bitmap

        // get the counts of each channel value for original and target channel
        int[] sourceChannelCounts = getCounts(sourceImage, maxVal, channel);
        int[] targetChannelCounts = getCounts(targetImage, maxVal, channel);

        // Calculate p_k cumulative sums for original and target channel
        double[] sourcePK = getCumSum(sourceChannelCounts);
        double[] targetPK = getCumSum(targetChannelCounts);

        // Calculate s_k values for original and target channel
        double[] sourceSK = divide(sourcePK, sourcePK[sourcePK.length - 1]);
        double[] targetSK = divide(targetPK, targetPK[targetPK.length - 1]);

        // construct inverse target s_k map
        HashMap<Double, ArrayList<Integer>> targetInverseSK = new HashMap<>();
        for (int level = 0; level <= maxVal; level++) {
            if (!targetInverseSK.containsKey(targetSK[level]))
                targetInverseSK.put(targetSK[level], new ArrayList<Integer>());
            targetInverseSK.get(targetSK[level]).add(level);
        }

        // find nearest target channel mappings for each source value
        HashMap<Integer, Integer> channelMappings = new HashMap<>();
        for (int level = 0; level <= sourceSK.length - 1; level++) {
            ArrayList<Integer> temp = targetInverseSK.get(findNearest2(sourceSK[level], targetSK));
            channelMappings.put(level, temp.get(temp.size() - 1));
        }

        // match channel
        for (int i = 0; i < matchedChannelImage.getHeight(); i++)
            for (int j = 0; j < matchedChannelImage.getWidth(); j++) {

                int sourcePixelColour = sourceImage.getPixel(j, i);
                int matchedVal;
                switch (channel) {
                    case RED:
                        matchedVal = channelMappings.get(Color.red(sourcePixelColour));
                        matchedChannelImage.setPixel(j, i, Color.argb(Color.alpha(sourcePixelColour), matchedVal, Color.green(sourcePixelColour), Color.blue(sourcePixelColour)));
                        break;
                    case GREEN:
                        matchedVal = channelMappings.get(Color.green(sourcePixelColour));
                        matchedChannelImage.setPixel(j, i, Color.argb(Color.alpha(sourcePixelColour), Color.red(sourcePixelColour), matchedVal, Color.blue(sourcePixelColour)));
                        break;
                    case BLUE:
                        matchedVal = channelMappings.get(Color.blue(sourcePixelColour));
                        matchedChannelImage.setPixel(j, i, Color.argb(Color.alpha(sourcePixelColour), Color.red(sourcePixelColour), Color.green(sourcePixelColour), matchedVal));
                        break;
                    case ALPHA:
                    default:
                        matchedVal = channelMappings.get(Color.green(sourcePixelColour));
                        matchedChannelImage.setPixel(j, i, Color.argb(matchedVal, Color.red(sourcePixelColour), Color.green(sourcePixelColour), Color.blue(sourcePixelColour)));
                        break;
                }

            }

        return matchedChannelImage;

    }

    private static int[] getCounts(Bitmap image, int maxVal, ColourChannel channel) {
        int[] counts = new int[maxVal + 1];
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                int pixelColour = image.getPixel(j, i);
                int channelValue;
                switch (channel) {
                    case RED:
                        channelValue = Color.red(pixelColour);
                        break;
                    case GREEN:
                        channelValue = Color.green(pixelColour);
                        break;
                    case BLUE:
                        channelValue = Color.blue(pixelColour);
                        break;
                    case ALPHA:
                    default:
                        channelValue = Color.alpha(pixelColour);
                        break;
                }

                counts[channelValue] += 1;
            }
        }

        return counts;
    }

    private static double[] getCumSum (int[] array) {
        double[] cumSumArray = new double[array.length];
        int currTotal = 0;
        for (int i = 0; i < array.length; i++) {
            currTotal += array[i];
            cumSumArray[i] = currTotal;
        }

        return cumSumArray;
    }

    private static double[] divide(double[] array, double value) {
        double[] out = new double[array.length];
        for (int i = 0; i < array.length; i++)
            out[i] = array[i] / value;
        return out;
    }

    private static double findNearest2(double val, double[] array) {
        double nearest = -1;
        double currMin = 100000000.0;
        for (double d : array) {
            if (Math.abs(d - val) < currMin) {
                nearest = d;
                currMin = Math.abs(d - val);
            }
        }

        return nearest;
    }
}

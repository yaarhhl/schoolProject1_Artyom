package com.example.school_project1;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;

import java.io.File;

public class Question {
    int ball;
    String answer;
    int imageId;
// развернутый ответ
    public Question(int ball, String answer, int imageId) {
        this.ball = ball;
        this.imageId = imageId;
        this.answer = answer;
    }
    // для вопросов с развернутым ответом
    public Question(int imageId) {
        this.imageId = imageId;
    }
}

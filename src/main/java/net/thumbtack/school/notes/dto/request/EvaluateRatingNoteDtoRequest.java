package net.thumbtack.school.notes.dto.request;

public class EvaluateRatingNoteDtoRequest {

    /*
    "rating":число от 1 до 5
     */

    private int rating;

    public EvaluateRatingNoteDtoRequest(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

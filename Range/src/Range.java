public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getRangesIntersection(Range range) {
        if (this.from <= range.from) {
            if (this.to >= range.to) {
                return this;
            }
        } else {
            if (range.to >= this.to) {
                return range;
            }
        }

        return null;
    }

    public Range getRangesUnion(Range range) {
        return null;
    }
}
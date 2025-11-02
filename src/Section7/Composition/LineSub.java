package Section7.Composition;

class Bo {
    protected String biMat = "Bố thích ăn kẹo!";  // private!

    public void keBiMat() {
        System.out.println(biMat);
    }

    // Bố tự đọc được
    void docNhatKy() {
        System.out.println(biMat);  // ĐƯỢC! Vì ở trong lớp Bo
        keBiMat();
    }
}

class Con extends Bo {
    void hoiBo() {
         System.out.println(biMat);  // LỖI! Không thấy được
         keBiMat();                  // LỖI! Không gọi được
    }
}
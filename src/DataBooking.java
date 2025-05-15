class DataBooking {
    private String nama;
    private String nik;
    private String notelp;
    private int awal;
    private int akhir;
    private int seatIndex;
    private String label;
    private String harga;

    private static final String[] Kota = {"Wilangan", "Ngawi", "Gendingan", "Solo", "Kartosuro", "Jogja", "Magelang"};

    public DataBooking(String nama, String nik, String telepon, int awal, int akhir, int seatIndex) {
        this.nama = nama;
        this.nik = nik;
        this.notelp = telepon;
        this.awal = awal;
        this.akhir = akhir;
        this.seatIndex = seatIndex;

        if (seatIndex < 18) {
            this.label = "A" + (seatIndex + 1);
        } else {
            this.label = "B" + (seatIndex - 17);
        }
        this.harga = getHarga(awal, akhir);
    }

    private String getHarga(int awal, int akhir) {
        String[][] harga = {
                {"Rute bus tidak valid", "Rp 35.000", "Rp 45.000", "Rp 55.000", "Rp 60.000", "Rp 70.000", "Rp 85.000"},
                {"Rp 35.000", "Rute bus tidak valid", "Rp 25.000", "Rp 30.000", "Rp 40.000", "Rp 45.000", "Rp 60.000"},
                {"Rp 45.000", "Rp 25.000", "Rute bus tidak valid", "Rp 25.000", "Rp 35.000", "Rp 40.000", "Rp 55.000"},
                {"Rp 55.000", "Rp 30.000", "Rp 25.000", "Rute bus tidak valid", "Rp 15.000", "Rp 15.000", "Rp 30.000"},
                {"Rp 60.000", "Rp 40.000", "Rp 35.000", "Rp 15.000", "Rute bus tidak valid", "Rp 15.000", "Rp 30.000"},
                {"Rp 70.000", "Rp 45.000", "Rp 40.000", "Rp 15.000", "Rp 15.000", "Rute bus tidak valid", "Rp 15.000"},
                {"Rp 85.000", "Rp 60.000", "Rp 55.000", "Rp 30.000", "Rp 30.000", "Rp 15.000", "Rute bus tidak valid"}
        };
        return harga[awal][akhir];
    }

    public String getNama() {
        return nama;
    }

    public String getNik() {
        return nik;
    }

    public String getNotelp() {
        return notelp;
    }

    public String getNamaLocAwal() {
        return Kota[awal];
    }

    public String getNamaLocAkhir() {
        return Kota[akhir];
    }

    public String getLabel() {
        return label;
    }

    public String getHarga() {
        return harga;
    }

    public int getSeatIndex() {
        return seatIndex;
    }

    public int getLokasiAwal() {
        return awal;
    }

    public int getLokasiAkhir() {
        return akhir;
    }
//    public int getTotalHarga(){
//        return (getHarga()*);
//    }
}

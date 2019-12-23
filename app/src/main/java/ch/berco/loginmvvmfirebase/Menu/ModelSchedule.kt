package ch.berco.loginmvvmfirebase.Menu

class ModelSchedule (
    var id: String,
    var namaTrainer: String,
    var materi: String,
    var ruangan: String,
    var durasi: String,
    var fotoTrainer: String,
    var hari: String
){
    constructor() : this ( "","","","","", "", ""){

    }
}
package com_proje.model.queries;

public class KuponQuery {

    public static final String saveKuponQuery = "INSERT INTO kupon(kuponId, kuponName) VALUES(?,?)";

    public static final String saveKupon_UserQuery = "INSERT INTO kupon_user(kuponId, userId) VALUES(?,?)";

    public static final String updateKuponQuery = "UPDATE kupon SET kuponName=?  WHERE kuponId =? ";

    public static final String deleteKupon_UserQuery = "DELETE FROM kupon_user WHERE kuponId =? ";
    public static final String deleteKuponByIdQuery = "DELETE FROM kupon WHERE kuponId =? ";

    public static final String findKuponByIdQuery = "SELECT * FROM kupon WHERE kuponId =? ";

    public static final String findKuponsQuery = "SELECT * FROM kupon";

    public static final String findKuponUsersByIdQuery = "SELECT * FROM kupon k LEFT OUTER JOIN kupon_user ku ON(k.kuponId = ku.kuponId) " +
            "LEFT JOIN user u ON(ku.userId = u.userId)  WHERE k.kuponId=?";
}

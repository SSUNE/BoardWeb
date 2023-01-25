package Controller;


import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDao {

    public int listcnt() {
        int result = -1;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {
            DBManager db = new DBManager();
            conn = db.connection();
            pstmt = conn.prepareStatement("select count(*) cnt from mvcboard3 order by bno desc");
            rset = pstmt.executeQuery();
            if (rset.next()) {
                result = rset.getInt("cnt");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public ArrayList<BDto> list10(int pstartno) {
        ArrayList<BDto> list = new ArrayList<BDto>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = "select * from mvcboard3 order by bno desc limit ?, ?";

        try {
            DBManager db = new DBManager();
            conn = db.connection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pstartno);
            pstmt.setInt(2, 10);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                list.add(new BDto(rset.getInt("bno"), rset.getString("bname"), rset.getString("bpass"),
                        rset.getString("btitle"), rset.getString("bcontent"), rset.getString("bdate"),
                        rset.getInt("bhit"), rset.getString("bip")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public ArrayList<BDto> listAll() {
        ArrayList<BDto> list = new ArrayList<BDto>();

        DBManager db = new DBManager();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        try {
            conn = db.connection();
            pstmt = conn.prepareStatement("select * from mvcboard3 order by bno desc");
            rset = pstmt.executeQuery();
            while (rset.next()) {
                list.add(new BDto(rset.getInt("bno"), rset.getString("bname"), rset.getString("bpass"),
                        rset.getString("btitle"), rset.getString("bcontent"), rset.getString("bdate"),
                        rset.getInt("bhit"), rset.getString("bip")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }// list

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int create(BDto dto) {

        int result = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "insert into mvcboard3(bname, bpass, btitle, bcontent, bip) values (?,?,?,?,?)";

        try {
            DBManager db = new DBManager();
            conn = db.connection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getBname());
            pstmt.setString(2, dto.getBpass());
            pstmt.setString(3, dto.getBtitle());
            pstmt.setString(4, dto.getBcontent());
            pstmt.setString(5, InetAddress.getLocalHost().getHostAddress());

            result = pstmt.executeUpdate(); //표

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int update_hit(BDto dto) {

        int result = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "update mvcboard3 set bhit=bhit+1 where bno=?";

        try {
            DBManager db = new DBManager();
            conn = db.connection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, dto.getBno());

            result = pstmt.executeUpdate();//표

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    public BDto read(BDto dto) {

        BDto result = new BDto();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = "select * from mvcboard3 where bno=?";

        try {
            DBManager db = new DBManager();
            conn = db.connection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, dto.getBno());

            rset = pstmt.executeQuery(); //표

            while (rset.next()) {
                result = new BDto(rset.getInt("bno"),
                        rset.getString("bname"),
                        rset.getString("bpass"),
                        rset.getString("btitle"),
                        rset.getString("bcontent"),
                        rset.getString("bdate"),
                        rset.getInt("bhit"),
                        rset.getString("bip"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int update(BDto dto) {

        int result = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "update mvcboard3 set btitle=?, bcontent=? where bno=? and bpass=?";

        try {
            DBManager db = new DBManager();
            conn = db.connection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getBtitle());
            pstmt.setString(2, dto.getBcontent());
            pstmt.setInt(3, dto.getBno());
            pstmt.setString(4, dto.getBpass());

            result = pstmt.executeUpdate(); //표

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        return result;

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int delete(BDto dto) {

        int result = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = "delete from mvcboard3 where bno=? and bpass=?";

        try {
            DBManager db = new DBManager();
            conn = db.connection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, dto.getBno());
            pstmt.setString(2, dto.getBpass());

            result = pstmt.executeUpdate(); //표

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;

    }

} // class BDao
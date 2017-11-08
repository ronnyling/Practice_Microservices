package SampleA;

import SampleA.Mapper.StaffMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class StaffRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Get the ads_id, ads_url and ads_fontColor
     */
    @Autowired
    Staff  getStaffInfo() {
            //jdbcTemplate.execute("DELETE FROM staff WHERE eid =1 ");
        /*final String sql = "SELECT `eid`, `f_name`, `l_name`, `gender`, `age`, `email`, `job_title`, `career_lvl`, `location`"
                + " WHERE `eid`=1";*/
                      //  "//NOW() >= start_date AND NOW() < end_date"
            System.out.println("Testing around");
            final String sql ="SELECT * FROM db_example.staff where eid=1";
            log.info("im in, does it work though.");
        try {
            log.info("im in repo try.");
            return jdbcTemplate.queryForObject(sql,new StaffMapper());
        } catch (Exception ex) {
            log.info("No staff info found.", ex);
            return null;
        }
    }


    Staff  getStaffInfo_byBranchID(String branchID) {
        final String sql ="SELECT * FROM db_example.staff where location=?";
        try {
            return jdbcTemplate.queryForObject(sql,new StaffMapper(),branchID);
        } catch (Exception ex) {
            log.info("No staff info found.", ex);
            return null;
        }
    }
    void registerStaff(Integer eID,String fName,String lName,String gender,Integer age,String email
            ,String jobTitle, String careerLvl,String location){
        final String insertSQL="INSERT INTO db_example.staff(eid, f_name, l_name, " +
                "gender, age, email, job_title, career_lvl, location) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(insertSQL,eID,fName,lName,gender,age,email,jobTitle,careerLvl,location);
    }
    void updateBranchID(String location,Integer eID){
        final String insertSQL1="update db_example.staff set " +
                "location=? where eid=?";
        jdbcTemplate.update(insertSQL1,location,eID);
    }
    void deleteStaff(Integer eID){
        final String insertSQL2="delete from db_example.staff " +
                "where eid=?";
        jdbcTemplate.update(insertSQL2,eID);
    }
}

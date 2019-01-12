package com.example.trantien.theflashquiz.mvc.models;

import com.example.trantien.theflashquiz.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by Zuka on 10/2/18.
 */
public class QuizBankWrapper {
    public static List<QuizBank> quizBanks;
    public static List<QuizBank> getAllQuestionBank(){
        if(quizBanks ==null){
            quizBanks = new ArrayList<>();
            quizBanks.add(new QuizBank.Builder()
                    .Questions(getQuestion1())
                    .Id("Option"+Utils.COUNT_FOLDER++)
                    .Owner("Admin")
                    .Topic("Bộ câu hỏi A")
                    .create());
            quizBanks.add(new QuizBank.Builder()
                    .Questions(getQuestion2())
                    .Id("Option"+Utils.COUNT_FOLDER++)
                    .Owner("Admin")
                    .Topic("Bộ câu hỏi B")
                    .create());
            quizBanks.add(new QuizBank.Builder()
                    .Questions(getQuestion3())
                    .Id("Option"+Utils.COUNT_FOLDER++)
                    .Owner("Admin")
                    .Topic("Bộ câu hỏi C")
                    .create());
        }
        return quizBanks;
    }

    public static RealmList<QuizModel> getQuestion1() {
        RealmList mList = new RealmList();
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Nước gì chỉ có đàn ông?")
                .Ans1("Nước Anh").Ans2("Nước Việt Nam").Ans3("Nước Áo").Ans4("Nước Mĩ").Rightans(1).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Trái gì sinh ra đã không nguyên vẹn?")
                .Ans1("Trái chuối").Ans2("Trái cóc").Ans3("Trái măng cụt").Ans4("Trái cam").Rightans(3).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Sữa gì không dùng để uống?")
                .Ans1("Sữa voi").Ans2("Sữa mẹ").Ans3("Sữa Ông Thọ").Ans4("Sữa tắm").Rightans(4).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Bán gì chỉ có một nửa?")
                .Ans1("Bán rau").Ans2("Bán muối").Ans3("Bán hành").Ans4("Bán kính").Rightans(4).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Đậu gì vô tình nhất?")
                .Ans1("Đậu phụ").Ans2("Đậu phộng").Ans3("Đậu rồng").Ans4("Đậu xanh").Rightans(1).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Đậu gì biết bay?")
                .Ans1("Đậu phụ").Ans2("Đậu phộng").Ans3("Đậu rồng").Ans4("Đậu xanh").Rightans(3).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("1 tờ giấy A4 thì gấp đôi được mấy lần?")
                .Ans1("1").Ans2("2").Ans3("3").Ans4("4").Rightans(1).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Cà gì đàn ông xài nhiều hơn đàn bà?")
                .Ans1("Cà tím").Ans2("Cà vạt").Ans3("Cà pháo").Ans4("Cà chua ").Rightans(2).create()
        );
        return mList;
    }
    public static RealmList<QuizModel> getQuestion2() {
        RealmList mList = new RealmList();
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Cá nào yếu nhất đại dương?")
                .Ans1("Cá cơm").Ans2("Cá vàng").Ans3("Cá mập").Ans4("Cá đuối").Rightans(4).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Một nhóm có 2 người cha và 2 người con chia đều 15 quả táo. Hỏi mỗi người được mấy quả?")
                .Ans1("3").Ans2("4").Ans3("5").Ans4("6").Rightans(4).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Ngọn núi ồn ào nhất Việt Nam?")
                .Ans1("Sơn La").Ans2("Fansipan").Ans3("Langbiang").Ans4("Bà Đen").Rightans(1).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Cái gì lúc cao lúc thấp, lúc to lúc nhỏ nhưng không ai thấy được?")
                .Ans1("Gậy Như Ý").Ans2("Bong bóng").Ans3("Giọng hát").Ans4("Tiền lương").Rightans(3).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Bánh xe nào không lăn khi xe hơi quẹo trái?")
                .Ans1("Bánh trước bên trái").Ans2("Bánh sau bên trái").Ans3("Bánh sau bên phải").Ans4("Bánh dự phòng").Rightans(4).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Cây gì lúc dài 2 màu, lúc ngắn chỉ 1 màu?")
                .Ans1("Cây nhang").Ans2("Cây cau").Ans3("Cây roi").Ans4("Cây số").Rightans(1).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Bánh gì mập mà không mập?")
                .Ans1("Bánh ít").Ans2("Bánh ú").Ans3("Bánh gai").Ans4("Bánh xe").Rightans(2).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Bánh gì ăn không được?")
                .Ans1("Bánh ít").Ans2("Bánh ú").Ans3("Bánh gai").Ans4("Bánh xe").Rightans(4).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Bánh gì ăn bao nhiêu cũng không đủ?")
                .Ans1("Bánh ít").Ans2("Bánh ú").Ans3("Bánh gai").Ans4("Bánh xe").Rightans(1).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Bánh gì tên nghe đã thấy đau?")
                .Ans1("Bánh ít").Ans2("Bánh ú").Ans3("Bánh gai").Ans4("Bánh xe").Rightans(3).create()
        );
        return mList;
    }
    public static RealmList<QuizModel> getQuestion3() {
        RealmList mList = new RealmList();
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Cái gì càng phơi khô càng ướt?")
                .Ans1("Cá nước trong").Ans2("Cá nước đục").Ans3("Cá cơm").Ans4("Cá vàng").Rightans(2).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Bay liên tục nhưng vẫn đứng yên một chỗ?")
                .Ans1("Lá cờ").Ans2("Con chim").Ans3("Máy bay").Ans4("Bong bóng").Rightans(1).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Cá nào có 4 chữ G?")
                .Ans1("Cá cơm").Ans2("Cá vàng").Ans3("Cá bống").Ans4("Cá đuối").Rightans(3).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Bút nào viết không ra mực?")
                .Ans1("Bút bi").Ans2("Bút tàng hình").Ans3("Bút lông").Ans4("Bút xóa").Rightans(4).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Bút nào viết không ra mực?")
                .Ans1("Lá cờ").Ans2("Con chim").Ans3("Máy bay").Ans4("Bong bóng").Rightans(1).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Rau gì không trồng không tưới, muôn hình muôn vẻ miệng cười khen ngon?")
                .Ans1("Rau muống").Ans2("Rau chuối").Ans3("Rau câu").Ans4("Rau xà lách").Rightans(3).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Khi nào màu vàng + màu vàng = màu đỏ?")
                .Ans1("Pha màu").Ans2("Coi phim 3D").Ans3("Phản xạ").Ans4("Bóng đá").Rightans(4).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Con gì hôm nay mưa mai ướt?")
                .Ans1("Con cá").Ans2("Con muỗi").Ans3("Con rùa").Ans4("Con ngựa").Rightans(3).create()
        );

        return mList;
    }
}

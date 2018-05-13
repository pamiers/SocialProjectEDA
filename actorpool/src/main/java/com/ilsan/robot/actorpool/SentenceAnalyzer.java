package com.ilsan.robot.actorpool;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.ilsan.robot.service.Sentence;
import kr.co.shineware.util.common.model.Pair;

public class SentenceAnalyzer extends UntypedActor {
    private ActorRef emotionActor;

    public SentenceAnalyzer() {
        emotionActor = context().actorOf(Props.create(EmotionActor.class), "emotionActor");
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Sentence) {
            Sentence sentence = (Sentence) message;

            for (int i=0; i<sentence.getPairs().size(); i++) {
                Pair<String, String> pair = sentence.getPairs().get(i);
                if (pair.getSecond().equals("NP") || pair.getSecond().equals("NNP") || pair.getSecond().equals("NNG")) {
                    if (i != sentence.getPairs().size() -1) {
                        Pair<String, String> josa = sentence.getPairs().get(i + 1);
                        if (josa.getSecond().equals("JKC") || josa.getSecond().equals("JX")) {
                            sentence.setSubject(pair.getFirst());
                        } else if (josa.getSecond().equals("JKO")){
                            sentence.setObjective(pair.getFirst());
                        }
                    }
                } else if (pair.getSecond().equals("VA")) {
                    if (i != sentence.getPairs().size() -1) {
                        Pair<String, String> umi = sentence.getPairs().get(i + 1);
                        if (umi.getSecond().equals("EC") || umi.getSecond().equals("EF")) {
                            sentence.setAdjective(pair.getFirst());
                        }
                    }
                } else if (pair.getSecond().equals("VV")) {
                    if (i != sentence.getPairs().size() -1) {
                        Pair<String, String> umi = sentence.getPairs().get(i + 1);
                        if (umi.getSecond().equals("EC") || umi.getSecond().equals("EF")) {
                            sentence.setVerb(pair.getFirst());
                        }
                    }
                }
            }
            System.out.println(sentence.toString());

        }


//        //-----------------------------------------------
//        // 주어 후보
//        //-----------------------------------------------
//        // 일반명사
//        List<String> nng = result.getMorphesByTags("NNG");
//        // 대명사
//        List<String> np = result.getMorphesByTags("NP");
//        // 고유명사
//        List<String> nnp = result.getMorphesByTags("NNP");
//
//        // 주격 조사
//        List<String> jkc = result.getMorphesByTags("JKC");
//
//
//        //-----------------------------------------------
//        // 서술어 후보
//        //-----------------------------------------------
//        // 동사
//        List<String> vv = result.getMorphesByTags("VV");
//        // 형용사
//        List<String> va = result.getMorphesByTags("VA");
//
//        //-----------------------------------------------
//        // 서술셕 주어 후보 ( 동상/형용사 + 의존명사)
//        //-----------------------------------------------
//        // 일반명사
//        List<String> nnb = result.getMorphesByTags("NNB");
//
//        //-----------------------------------------------
//        // 목적어 후보
//        //-----------------------------------------------
//        // 목적격 조사
//        List<String> jko = result.getMorphesByTags("JKO");
//
//        //-----------------------------------------------
//        // 감탄사 --> Reaction
//        //-----------------------------------------------
//        // 감탄사
//        List<String> ic = result.getMorphesByTags("IC");
//
//        //-----------------------------------------------
//        // 종결 어미 -->
//        //-----------------------------------------------
//        // 종결 어미
//            /*
//          평서문 어미
//         -다/-는다-/-ㄴ다
//         -구나/-는구나
//         -군/는군
//         -네
//         -으마/-마
//         -을걸/-ㄹ걸
//         -을게/ㄹ게, -을래/-ㄹ래
//         -을라/-ㄹ라, -는단다/-ㄴ단다/-단다/-란다
//         의문문 어미
//         -으냐/-냐/-느냐, -으니/-니
//         -련
//         -으랴/-랴, -을쏘냐/-ㄹ쏘냐
//         -대, -담
//         명령문 어미
//         -아라/-어라/-여라
//         -으려무나/려무나, -으렴/-렴
//         -소서
//         -아/-어, -지
//         -아/-어
//         -지
//         후종결어미
//         -고
//         -니까
//         -며, -면서
//         인용어미
//         -단다, -느냔다, -란다, -잔다
//         -대
//             */
//        List<String> ef = result.getMorphesByTags("EF");
//
//        List<String> vcp = result.getMorphesByTags("VCP");
//        List<String> vcn = result.getMorphesByTags("VCN");
//
//        List<String> ec = result.getMorphesByTags("EC");
//
//
//
//        System.out.println("[긍정]=[" + vcp + "]");
//        System.out.println("[부정]=[" + vcn + "]");
//        System.out.println("[종결어미]=[" + ef + "]");
//        System.out.println("[감탄사]=[" + ic + "]");
//        System.out.println("[목적조사]=[" + jko + "]");
//        System.out.println("[일반명사]=[" + nnb + "]");
//        System.out.println("[동사]=[" + vv + "]");
//        System.out.println("[형용사]=[" + va + "]");
//        System.out.println("[주격조사]=[" + jkc + "]");
//        System.out.println("[고유명사]=[" + nnp + "]");
//        System.out.println("[일반명사]=[" + nng + "]");
//        System.out.println("[대명사]=[" + np + "]");
//        System.out.println("[연결어미]=[" + ec + "]");
//
//
//        // 주어 추출
//
//        // 목적어 추출
//
//        // 서술어 추출
//
//        // 시간 부사 추출
//
//        // 장소 부사 추출
//
//        // 바램?
//
//        // 질문?
//
//        // 요청?
//
//        // 설득?
//
//
////            emotionActor.tell(msg, getSelf());
    }
}

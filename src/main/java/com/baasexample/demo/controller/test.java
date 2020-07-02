package com.baasexample.demo.controller;

import com.alibaba.fastjson.JSON;
import com.baasexample.demo.Mapper.NetMapper;
import com.baasexample.demo.model.dbMo.NetInfo;
import com.baasexample.demo.model.jsonMo.channelJsonModel;
import com.baasexample.demo.model.jsonMo.jsonModel;
import com.baasexample.demo.model.yamlMo.BlockData;
import com.baasexample.demo.model.yamlMo.Orderer;
import com.baasexample.demo.model.yamlMo.Organization;
import com.baasexample.demo.model.yamlMo.Peer;
import com.baasexample.demo.service.*;
import com.sun.org.apache.xpath.internal.operations.Or;
import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import com.google.common.io.ByteStreams;
import io.kubernetes.client.Exec;
import io.kubernetes.client.util.Config;
import com.offbytwo.jenkins.JenkinsServer;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.cli.*;
import org.apache.commons.io.IOUtils;


/**
 * 注释写在这
 *
 * @author Monhey
 */
public class test {
//    private static FabricYamlGenerateService k;
//    public static void testFastJson()throws IOException {
        String s ="{\n" +
                "\t\"name\": \"test\", \n" +
                "\t\"order\":[\n" +
                "\t\t{\n" +
                "        \t\"orderName\": \"order0\",\n" +
                "        \t\"orderPort\": \"8050\"\n" +
                "    \t}\n" +
                "\t], \n" +
                "    \"org\":[\n" +
                "    \t{\n" +
                "\t        \"orgName\": \"org1\",\n" +
                "\t        \"peers\": [\n" +
                "\t        \t{\n" +
                "\t                \"peerName\": \"peer0-org1\",\n" +
                "\t                \"ports\": [\"8051\", \"8052\", \"8053\", \"9443\"]\n" +
                "\t            }\n" +
                "\t        ]\n" +
                "        }\n" +
                "    \t{\n" +
                "\t        \"orgName\": \"org2\",\n" +
                "\t        \"peers\": [\n" +
                "\t        \t{\n" +
                "\t                \"peerName\": \"peer0-org1\",\n" +
                "\t                \"ports\": [\"8051\", \"8052\", \"8053\", \"9443\"]\n" +
                "\t            }\n" +
                "\t        ]\n" +
                "        }\n" +
                "    ], \n" +
                "    \"consensus\": \"etcdraft\",\n" +
                "    \"tls\": true\n" +
                "}";
//        String t="name=test, order=[Orderer(orderName=order0, orderport=8050)], org=[Organization(orgName=org1, peers=[Peer(peerName=peer0-org1, ports=[8051, 8052, 8053, 9443])])], consensus=etcdraft, tls=true";
//
//        jsonModel js = JSON.parseObject(s,jsonModel.class);
//        List<Organization> list =js.getOrg();
//        System.out.println(js.toString());
////        System.out.println(list.toString());
//    }
//
//    public static void testFastJson1()throws IOException {
//        String ss="{\n" +
//                "\t\"name\": \"mychannel\"\n" +
//                "\t\"networkId\": \"6\",\n" +
//                "\t\"peers\": [\"peer0-org1\", \"peer1-org1\"]\n" +
//                "}";
//        channelJsonModel js = JSON.parseObject(ss,channelJsonModel.class);
//        System.out.println(js.toString());
//    }
////    public static void testCrypto() throws IOException {
////        k = new FabricYamlGenerateService();
////        Orderer o = new Orderer();
////        o.setOrderName("order0");
////        Orderer oo = new Orderer();
////        oo.setOrderName("order1");
////        List<Orderer> ordererList = new ArrayList<>();
////        ordererList.add(o);
////        ordererList.add(oo);
////        List<Organization> orgList = new ArrayList<>();
////        Organization o1 =new Organization();
////        Organization o2 = new Organization();
////        o2.setOrgName("org2");
////        o1.setOrgName("org1");
////        List<Peer> peers = new ArrayList<>();
////        Peer p1 = new Peer();
////        Peer p2 = new Peer();
////        p1.setPeerName("peer1");
////        p2.setPeerName("peer2");
////        List<Integer> portList = new ArrayList<>();
////        portList.add(8080);
////        p1.setPorts(portList);
////        p2.setPorts(portList);
////        peers.add(p1);
////        peers.add(p2);
////        o1.setPeers(peers);
////        o2.setPeers(peers);
////        orgList.add(o1);
////        orgList.add(o2);
////        k.replaceWithConfigtxTemplate(orgList,ordererList,"etcdraft");
////        k.replaceWithCryptoconfigTemplate(ordererList,orgList);
////    }
////    public static List<Orderer> testList(){
////        List<Orderer> list = new ArrayList<>();
////        for(int i=0;i<3;i++){
////            Orderer orderer = new Orderer();
////            orderer.setOrderName(String.valueOf(i));
////            orderer.setOrderport(i);
////            list.add(orderer);
////        }
////        return list;
////    }
//
//    /**
//     * 读取文件
//     * @param sourcePath 文件所在的网络路径
//     */
//    public static BlockData readHtmlFile(String sourcePath){
//        String line;
//        int lineNum=0;
//        List<String> dataList = new ArrayList<>();
//        BufferedReader reader=null;
//        BlockData bd = new BlockData();
//        try{
//            URL url = new URL(sourcePath);
//            reader = new BufferedReader(new InputStreamReader(url.openStream()));
//            while ((line = reader.readLine()) != null){
//                lineNum++;
//                if(line.contains("data_hash")){
//                    bd.setDataHash(line.split("\"")[3]);
//                }
//                if(line.contains("number")){
//                    bd.setBlockNum(Integer.parseInt(line.split("\"")[3]));
//                }
//                if(line.contains("previous_hash")){
//                    bd.setPreHash(line.split("\"")[3]);
//                }
//                if(line.contains("timestamp")){
//                    bd.setTimeStamp(line.split("\"")[3]);
//                }
//            }
//        }
//        catch (Exception ie){
//            ie.printStackTrace();
//        }finally{
//            try{
//                if(reader != null)
//                    reader.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        return bd;
//    }
    public static void testtt()
            throws IOException, ApiException, InterruptedException, ParseException {
        ByteArrayOutputStream baoStream = new ByteArrayOutputStream(1024*1024);
        PrintStream cacheStream = new PrintStream(baoStream);
        PrintStream oldStream = System.out;
        System.setOut(cacheStream);

        final Options options = new Options();
        k8sClientService k = new k8sClientService();
        k.setK8sClient();
        options.addOption(new Option("p", "pod", true, "The name of the pod"));
        options.addOption(new Option("n", "namespace", true, "The namespace of the pod"));
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, null);
        String podName = cmd.getOptionValue("p", "cli-org1-6dc897958b-ldhmq");
        String namespace = cmd.getOptionValue("n", "hyperledger");
        List<String> commands = new ArrayList<>();
        Exec exec = new Exec();
        boolean tty = System.console() != null;
        final Process proc =
                exec.exec(
                        namespace,
                        podName,
                        commands.isEmpty()
                                ? new String[] {"sh","-c","peer lifecycle chaincode queryinstalled"}
                                : commands.toArray(new String[commands.size()]),
                        true,
                        tty);

        Thread in =
                new Thread(
                        new Runnable() {
                            public void run() {
                                try {
                                    ByteStreams.copy(System.in, proc.getOutputStream());
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
        in.start();
        Thread out =
                new Thread(
                        new Runnable() {
                            public void run() {
                                try {
                                     String res = IOUtils.toString(proc.getInputStream(), "utf-8");
                                    System.out.println(res);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
        out.start();

        proc.waitFor();

        // wait for any last output; no need to wait for input thread
        out.join();

        proc.destroy();
        String res = baoStream.toString();
        System.setOut(oldStream);
        String ccid = res.split("ID:")[1].split(",")[0].trim();
        System.out.println(ccid);
    }
//
    public static void main(String[] args) throws IOException, InterruptedException, ApiException, ParseException {
        JsonService jsonService = new JsonService();
        String netWorkJson="{\n" +
                "\t\"name\": \"fuck\", \n" +
                "\t\"order\":[\n" +
                "\t\t{\n" +
                "        \t\"orderName\": \"orderer0\",\n" +
                "        \t\"orderPort\": \"7050\"\n" +
                "    \t},{\n" +
                "            \"orderName\": \"orderer1\",\n" +
                "        \t\"orderPort\": \"7050\"\n" +
                "        },{\n" +
                "            \"orderName\": \"orderer2\",\n" +
                "        \t\"orderPort\": \"7050\"\n" +
                "        }\n" +
                "\t], \n" +
                "    \"org\":[\n" +
                "    \t{\n" +
                "\t        \"orgName\": \"org1\",\n" +
                "\t        \"peers\": [\n" +
                "\t        \t{\n" +
                "\t                \"peerName\": \"peer0-org1\",\n" +
                "\t                \"ports\": [\"7051\", \"8052\", \"8053\", \"9443\"]\n" +
                "\t            }\n" +
                "\t        ]\n" +
                "        },{\n" +
                "            \"orgName\": \"org2\",\n" +
                "\t        \"peers\": [\n" +
                "\t        \t{\n" +
                "\t                \"peerName\": \"peer0-org2\",\n" +
                "\t                \"ports\": [\"7051\", \"8052\", \"8053\", \"9443\"]\n" +
                "\t            }\n" +
                "\t        ]\n" +
                "        }\n" +
                "    ], \n" +
                "    \"consensus\": \"etcdraft\",\n" +
                "    \"tls\": true\n" +
                "}";
//        jsonModel jm = jsonService.CastJsonToNetBean(netWorkJson);
//        K8sYamlGenerateService k8sYamlGenerateService = new K8sYamlGenerateService();
//        FabricYamlGenerateService fabricYamlGenerateService = new FabricYamlGenerateService();
//        String NameSpace = jm.getName();
//        List<Orderer> orderList = jm.getOrder();
//        List<Organization> orgList = jm.getOrg();
//        fabricYamlGenerateService.replaceWithConfigtxTemplate(NameSpace,orgList,orderList,jm.getConsensus());
//        fabricYamlGenerateService.replaceWithCryptoconfigTemplate(NameSpace,orderList,orgList);
//        for(Orderer orderer:orderList){
//            String ordererName = orderer.getOrderName();
//            List<String> otherOrdererList = new ArrayList<>();
//            for(Orderer o:orderList){
//                if(!o.getOrderName().equals(ordererName))
//                    otherOrdererList.add(o.getOrderName());
//            }
//            k8sYamlGenerateService.generateOrdererDeploymentYaml(ordererName,otherOrdererList,NameSpace);
//            k8sYamlGenerateService.generateOrdererServiceYaml(ordererName,NameSpace);
//        }
//        for(Organization org:orgList){
//            List<Peer> peerList = org.getPeers();
//            String orgName = org.getOrgName();
//            k8sYamlGenerateService.generateCaDeploymentYaml(orgName,NameSpace);
//            k8sYamlGenerateService.generateCaServiceYaml(orgName,NameSpace);
//            k8sYamlGenerateService.generateCliDeploymentYaml(orgName,NameSpace);
//            for(Peer p:peerList){
//                String peerName = p.getPeerName();
//                k8sYamlGenerateService.generatePeerDeploymentYaml(peerName,NameSpace,orgName);
//                k8sYamlGenerateService.generatePeerServiceYaml(peerName,NameSpace);
//            }
//        }
//        k8sYamlGenerateService.generateConfigMap(NameSpace);
    }
}

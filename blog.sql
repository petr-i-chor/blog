/*
Navicat MySQL Data Transfer

Source Server         : 192.168.2.16-mysql
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2021-08-16 21:35:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_blog`
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `first_picture` varchar(255) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `appreciation` int(11) NOT NULL DEFAULT '0',
  `share_statement` int(11) NOT NULL DEFAULT '0',
  `commentabled` int(11) NOT NULL DEFAULT '0',
  `published` int(11) NOT NULL DEFAULT '0',
  `recommend` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `description` text,
  `tag_ids` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES ('13', '博客内容测试', '开发美而小的博客', 'https://iknow-pic.cdn.bcebos.com/4034970a304e251fae75ad03a786c9177e3e534e', '', '0', '1', '1', '1', '1', '1', '2021-08-03 14:53:34', '2021-08-08 10:51:06', '14', '1', '今天又是魔幻现实的一天', '10,15,16,14');
INSERT INTO `t_blog` VALUES ('14', 'redis RDB持久化不生效', '# redis配置持久化：\r\n```shell\r\nsave 900 1              #在900秒(15分钟)之后，如果至少有1个key发生变化，则dump内存快照。\r\nsave 300 10            #在300秒(5分钟)之后，如果至少有10个key发生变化，则dump内存快照。\r\nsave 60 10000        #在60秒(1分钟)之后，如果至少有10000个key发生变化，则dump内存快照\r\n```\r\n\r\n# 但是重启了之后还是没有生效，是因为没有指定文件目录和文件名：\r\n````shell\r\ndir ./\r\ndbfilename my_redis.rdb\r\n````\r\n`', 'https://hbimg.huabanimg.com/3e24965fb20beaab32dec5400f2539c0d828605f2df61-FqT2Af_fw658/format/webp', '', '0', '1', '1', '1', '1', '1', '2021-08-07 10:04:26', '2021-08-07 10:04:26', '15', '1', '学习RedisRDB和AOF中遇到的小问题', '13,14');
INSERT INTO `t_blog` VALUES ('15', ' 最小k个数，快速排序算法', '快排思想 将小于基准数的数全放在基准数的左边，大于的基准数的数全放在基准数的右边\r\n基准数 pivot（本道题选取数组最左边的数为基准数）\r\n左右指针 p,q\r\n先将pivot取出来。\r\np指向pivot，arr[p]上的值为无效值，q开始移动，当q指向小于pivot的index时，arr[p]=arr[q]。\r\narr[q]上的值为无效值，p开始移动，当q指向大于pivot的index时，arr[q]=arr[p]。\r\n\r\n临界条件\r\np=q(p一定会等于q，因为判断条件为在p<q，且p和q的步长为1)\r\n因为p和q不动的一方上面的值为无效值，所以最后arr[q]=pivot，第一次排序完成\r\n\r\n将pivot左右两边的数组进行递归，则能够将整个数组排序\r\n\r\n递归出口\r\n当左边或右边只有一个数的时候，return ;\r\n换个思路，只有left<right的时候，整个排序才能进行，left<right可作为整个程序判断的基准\r\n\r\n注意点：①不管是p和q轮流走还是p和q一起走然后交换，p一定会等于q（困扰我许久，老是想p>q的情况）\r\n②判断条件arr[p]得<=pivot或者arr[q]>=pivot二选一，若不选会出现arr[p]和arr[q]相等在那里死循环交换\r\n\r\n失败经验：之前每次交换值指针会移动一格，导致边界的不确定性，血的教训。指针的定义一定要严格规定\r\n```java\r\nclass Solution {\r\n     public int[] getLeastNumbers(int[] arr, int k) {\r\n\r\n        quickSort(arr,0,arr.length-1);\r\n        int[] nums = new int[k];\r\n        for (int i = 0; i < k; i++) {\r\n            nums[i] = arr[i];\r\n        }\r\n        return nums;\r\n    }\r\n\r\n        void quickSort(int [] arr,int left,int right){\r\n\r\n        if (left<right){\r\n            int pivot = arr[left];\r\n            int p = left;\r\n            int q = right;\r\n            while (p<q){\r\n                while (p<q&&arr[q]>pivot){\r\n                    q--;\r\n                }\r\n\r\n                if (p<q){\r\n                    arr[p]=arr[q];\r\n                }\r\n\r\n                while (p<q&&arr[p]<=pivot){\r\n                    p++;\r\n                }\r\n                if (p<q){\r\n                    arr[q]=arr[p];\r\n                }\r\n            }\r\n            arr[p]=pivot;\r\n            quickSort(arr,left,q-1);\r\n            quickSort(arr,q+1,right);\r\n        }\r\n        else{\r\n            return ;\r\n        }\r\n    }\r\n\r\n}\r\n\r\n```\r\n', 'https://hbimg.huabanimg.com/7d7683aea2e1de5f1aca1e50d244c4ce4a2578cf44b1b-8OBqRx_fw658/format/webp', '', '0', '0', '0', '0', '1', '0', '2021-08-07 10:11:11', '2021-08-07 10:11:11', '16', '1', '十大经典排序之一——快速排序', '10,17,16');
INSERT INTO `t_blog` VALUES ('16', '无重复字符的最长子串', '###  解题思路\r\nHashMap<> key：字符 value: 字符最后一次出现的索引\r\nans：存放最大无重复字符子串\r\nend：指针，每次循环向右移动一次\r\nstart(重难点)：无重复子串的起点，起始为0.\r\n每次遇到重复字符时，start都需要更新。\r\n两种情况：①重复出现字符的上一次出现的位置（暂时用a） < 当前start 结果：若取a+1作为新的start则 会导致end到a+1的子串出现重复字符，注意：start是无重复子串的起点。所以不可取。\r\n② 重复出现字符的上一次出现的位置（暂时用b） > 当前start 结果：取b+1作为新的start，能保证a+1到end不会出现重复字符，可取。（为什么能保证子串不会出现重复字符，因为一有重复字符start就更新）\r\n\r\nstart只管保持与end的之间子串没有重复字符\r\n其他的都留给ans\r\n\r\nans每次循环都会更新最长子串\r\n\r\nstart与ans分工合作，解决这道难题\r\n\r\n###  代码\r\n\r\n```java\r\nclass Solution {\r\npublic int lengthOfLongestSubstring(String s) {\r\n        if (s.equals(\"\")){\r\n            return 0;\r\n        }\r\n        Map<Character, Integer> map = new HashMap<>();\r\n\r\n        int ans = 0;\r\n\r\n        for (int end = 0, start = 0; end <s.length() ; end++) {\r\n            if (map.containsKey(s.charAt(end))){\r\n                 start = Math.max(start,map.get(s.charAt(end))+1);\r\n            }\r\n            ans = Math.max(ans,end - start + 1);\r\n            map.put(s.charAt(end),end);\r\n        }\r\n        return ans;\r\n    }\r\n\r\n}\r\n\r\n```\r\n\r\n', 'https://hbimg.huabanimg.com/8d6c49d5f57f309614d32565d9ef7f31aa7633d040a17-djQA9j_fw658/format/webp', '', '0', '0', '0', '0', '1', '0', '2021-08-07 10:26:41', '2021-08-08 10:44:55', '17', '1', '给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。（滑动窗口解决）', '17,10,11');
INSERT INTO `t_blog` VALUES ('17', '剑指 Offer 04. 二维数组中的查找', '### 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。\r\n\r\n来源：力扣（LeetCode）\r\n链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof\r\n\r\n### 解题思路：\r\n1 需要两个指针col和row，col代表每次循环从第index行第col列开始，row代表每次循环从第row行第index列开始\r\n2 从尾到头遍历第p行上的数，若找到target，返回true，否则找到<target的数就退出循环，记录当前列数。\r\n从尾到头遍历第q列上的数，若找到target，返回true，否则找到<target的数就退出循环，记录当前行数。\r\n3 若没有找到target，index就++,进行下一次循环\r\n4 终止条件，index>row || index>col,返回false，说明target不存在\r\n\r\n### 解题代码\r\n```java\r\nclass Solution {\r\n    public boolean findNumberIn2DArray(int[][] matrix, int target) {\r\n        if (matrix.length==0){\r\n            return false;\r\n        }\r\n        int row = matrix.length-1;//行\r\n        int col = matrix[0].length-1;//列\r\n        int index = 0;\r\n\r\n        while (index<=row && index<=col) {\r\n\r\n            for (int i = row; i >= index; i--) {\r\n                if (matrix[i][index] == target) {  \r\n                    return true;\r\n                } else if (matrix[i][index] < target) {\r\n                    row = i;\r\n                    break;\r\n                }\r\n            }\r\n\r\n\r\n            for (int j = col; j >= index; j--) {\r\n                if (matrix[index][j] == target) {  \r\n                    return true;\r\n                } else if (matrix[index][j] < target) {\r\n                    col = j;\r\n                    break;\r\n                }\r\n            }\r\n\r\n            index++;\r\n        }\r\n        return false;\r\n    }\r\n}\r\n\r\n\r\n\r\n```', 'https://hbimg.huabanimg.com/316d209b5fba8f387b16c51b9bddc93eab8891ac2d896-70lsWP_fw658/format/webp', '', '0', '1', '1', '1', '1', '1', '2021-08-07 10:32:58', '2021-08-07 10:32:58', '17', '1', '剑指 Offer 04. 二维数组中的查找', '10,17,16');
INSERT INTO `t_blog` VALUES ('18', '博客内容保存测试', '测试保存功能', 'https://img1.baidu.com/it/u=3587672767,2372851483&fm=26&fmt=auto&gp=0.jpg', '', '0', '1', '1', '1', '0', '0', '2021-08-12 15:42:35', '2021-08-12 15:57:13', '15', '1', '今天也是魔幻现实的一天', '10,15,16,17');
INSERT INTO `t_blog` VALUES ('19', 'Redis排行榜测试', '测试博客点击量和热度的联系', 'https://img0.baidu.com/it/u=4230220928,3282947183&fm=26&fmt=auto&gp=0.jpg', '', '0', '0', '0', '0', '1', '0', '2021-08-16 15:33:44', '2021-08-16 15:33:44', '15', '1', 'redis的应用场景之排行榜', '13');
INSERT INTO `t_blog` VALUES ('20', '页面跳转bug测试', '页面跳转bug测试', 'https://hbimg.huabanimg.com/e18496270d26a4d87184532c7f75d0b24b7e94ab274e1-O6JQrD_fw658/format/webp', '', '0', '1', '1', '1', '1', '1', '2021-08-16 20:03:52', '2021-08-16 20:03:52', '15', '1', '页面跳转不到对应页', '10,15,16');

-- ----------------------------
-- Table structure for `t_blog_tags`
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tags`;
CREATE TABLE `t_blog_tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_id` bigint(20) DEFAULT NULL,
  `blog_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_blog_tags
-- ----------------------------
INSERT INTO `t_blog_tags` VALUES ('98', '13', '14');
INSERT INTO `t_blog_tags` VALUES ('99', '14', '14');
INSERT INTO `t_blog_tags` VALUES ('100', '10', '15');
INSERT INTO `t_blog_tags` VALUES ('101', '17', '15');
INSERT INTO `t_blog_tags` VALUES ('102', '16', '15');
INSERT INTO `t_blog_tags` VALUES ('106', '10', '17');
INSERT INTO `t_blog_tags` VALUES ('107', '17', '17');
INSERT INTO `t_blog_tags` VALUES ('108', '16', '17');
INSERT INTO `t_blog_tags` VALUES ('116', '17', '16');
INSERT INTO `t_blog_tags` VALUES ('117', '10', '16');
INSERT INTO `t_blog_tags` VALUES ('118', '11', '16');
INSERT INTO `t_blog_tags` VALUES ('119', '10', '13');
INSERT INTO `t_blog_tags` VALUES ('120', '15', '13');
INSERT INTO `t_blog_tags` VALUES ('121', '16', '13');
INSERT INTO `t_blog_tags` VALUES ('122', '14', '13');
INSERT INTO `t_blog_tags` VALUES ('131', '10', '18');
INSERT INTO `t_blog_tags` VALUES ('132', '15', '18');
INSERT INTO `t_blog_tags` VALUES ('133', '16', '18');
INSERT INTO `t_blog_tags` VALUES ('134', '17', '18');
INSERT INTO `t_blog_tags` VALUES ('135', '13', '19');
INSERT INTO `t_blog_tags` VALUES ('136', '10', '20');
INSERT INTO `t_blog_tags` VALUES ('137', '15', '20');
INSERT INTO `t_blog_tags` VALUES ('138', '16', '20');

-- ----------------------------
-- Table structure for `t_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `blog_id` bigint(20) DEFAULT NULL,
  `parent_comment_id` bigint(20) DEFAULT NULL,
  `admincomment` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES ('19', '魔法so神奇', '2959543063@qq.com', '语音内容：姚佳！！你家一根管五毛钱啊！你真是喝奶茶没管——欠管！一天天的，咋这么欠呢！', '/images/avatar.jpg', '2021-08-10 15:43:25', '13', null, '0');
INSERT INTO `t_comment` VALUES ('20', '小胡开始智障', '2959543063@qq.com', '奇怪的歇后语增加了', '/images/avatar.jpg', '2021-08-10 15:44:59', '13', '19', '0');
INSERT INTO `t_comment` VALUES ('21', '-闻柒-', '2959543063@qq.com', '我晒干了寂寞,悔的很冲动', '/images/avatar.jpg', '2021-08-10 15:46:17', '13', '19', '0');
INSERT INTO `t_comment` VALUES ('22', '-楠猫零Brilliant', '2959543063@qq.com', '在，为什么你可以发语音', '/images/avatar.jpg', '2021-08-10 15:46:33', '13', '21', '0');
INSERT INTO `t_comment` VALUES ('23', '电个鸟灯泡', '2959543063@qq.com', '能不能给我一首歌的时间～', '/images/avatar.jpg', '2021-08-10 15:47:08', '13', '21', '0');
INSERT INTO `t_comment` VALUES ('24', '局外人_l', '2959543063@qq.com', '呦，又复活了', '/images/avatar.jpg', '2021-08-10 15:50:31', '13', null, '0');
INSERT INTO `t_comment` VALUES ('25', '乃淇淋屁用没有捏', '2959543063@qq.com', '热评密码', '/images/avatar.jpg', '2021-08-10 15:50:44', '13', '24', '0');
INSERT INTO `t_comment` VALUES ('26', '心奎', '2959543063@qq.com', '借楼问一下这是哪家医院，我挺想去的', '/images/avatar.jpg', '2021-08-10 15:51:05', '13', null, '0');
INSERT INTO `t_comment` VALUES ('27', '心奎', '2959543063@qq.com', '借楼问一下这是哪家医院，我挺想去的', '/images/avatar.jpg', '2021-08-10 15:51:27', '13', '24', '0');
INSERT INTO `t_comment` VALUES ('28', '局外人_l', '2959543063@qq.com', '你需要一个长得像呱太的医生', '/images/avatar.jpg', '2021-08-10 15:51:56', '13', '27', '0');
INSERT INTO `t_comment` VALUES ('29', '锦瑟流年XYY', '2959543063@qq.com', '应该是长得像医生的呱太', '/images/avatar.jpg', '2021-08-10 15:52:18', '13', '28', '0');
INSERT INTO `t_comment` VALUES ('30', '局内人', '2959543063@qq.com', '应该是长得像医呱的生太', '/images/avatar.jpg', '2021-08-10 15:57:17', '13', '29', '0');
INSERT INTO `t_comment` VALUES ('31', '-闻柒-', '2959543063@qq.com', '就算这是做错 也只是怕错过', '/images/avatar.jpg', '2021-08-10 16:01:29', '13', '22', '0');
INSERT INTO `t_comment` VALUES ('32', '昱门关', 'gem47@qq.com', '凯菇像赵云一样七进七出，怪力也很棒，在凯菇怀里没有哭', '/images/avatar.jpg', '2021-08-16 19:54:02', '14', null, '0');
INSERT INTO `t_comment` VALUES ('33', '容若楚河', 'gem47@qq.com', '发生甚么事了', '/images/avatar.jpg', '2021-08-16 19:54:47', '14', '32', '0');

-- ----------------------------
-- Table structure for `t_tag`
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES ('10', 'java');
INSERT INTO `t_tag` VALUES ('11', 'JavaWeb');
INSERT INTO `t_tag` VALUES ('12', 'MySQL');
INSERT INTO `t_tag` VALUES ('13', 'Redis');
INSERT INTO `t_tag` VALUES ('14', 'SpringMVC');
INSERT INTO `t_tag` VALUES ('15', 'SpringBoot');
INSERT INTO `t_tag` VALUES ('16', 'MyBatis');
INSERT INTO `t_tag` VALUES ('17', 'LeetCode');

-- ----------------------------
-- Table structure for `t_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES ('8', '面试');
INSERT INTO `t_type` VALUES ('9', 'java集合');
INSERT INTO `t_type` VALUES ('10', 'Java多线程');
INSERT INTO `t_type` VALUES ('11', 'javaIO');
INSERT INTO `t_type` VALUES ('12', 'SpringMVC源码分析');
INSERT INTO `t_type` VALUES ('13', 'Spring Boot 源码');
INSERT INTO `t_type` VALUES ('14', '美而小的博客');
INSERT INTO `t_type` VALUES ('15', '编程中遇到的问题');
INSERT INTO `t_type` VALUES ('16', '排序');
INSERT INTO `t_type` VALUES ('17', '剑指offer');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `type` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'jianghuan', 'jianghuan', 'e10adc3949ba59abbe56e057f20f883e', '2959543063@qq.com', '', '1', '2021-07-14 18:30:34', null);

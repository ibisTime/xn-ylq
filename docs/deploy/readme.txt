部署步骤：
1，eclipse导出war包

2. 本地war包上传至服务器
   scp -P 22 yc-mall.war root@121.43.101.148:/home
   T6dh%$%$ss1

3. 备份原先配置(如果第一次部署，跳过此步骤)
  ssh root@121.43.101.148 -p 22
  T6dh%$%$ss1

  cd /home/wwwroot/yaocheng/tomcat_yc_mall/webapps
  cp ./yc-mall/WEB-INF/classes/application.properties .
  cp ./yc-mall/WEB-INF/classes/config.properties .
  rm -rf yc-mall*
  mv /home/yc-mall.war .
   
4. 已备份配置文件放回原处,重启tomcat
  mv -f application.properties ./yc-mall/WEB-INF/classes/
  mv -f config.properties ./yc-mall/WEB-INF/classes/
  
  ../bin/shutdown.sh
  ../bin/startup.sh
  
6. 验证程序
http://121.43.101.148:3502/yc-mall/api


Group
Unpolished
member
นายกันตินันท์ มีสุข 63090500401
นายฐิติพงศ์ ทองคำ 63090500424
นายภูดิส ถาวรสุสิน 63090500432

ขั้นตอนการออกแบบโปรแกรม
คิดรูปร่าและลักษณะของเกม
ศึกษาการทำผ่านทางสื่อต่างๆ
ออกแบบตัวละคร แผนที่
ทำให้ตัวละครเคลื่อนที่ได้
ทำให้ตัวละครมีพลังชีวิต (เลือด)
ทำระเบิด เมื่อตัวละครอยู่ในตำแหน่งเดียวกับระเบิด เลือดของตัวละครจะลดลงและระเบิดจะหายไป
ทำ item เพิ่มเลือดของตัวละคร เมื่อตัวละครอยู่ในตำแหน่งเดียวกับ item นี้ เลือดของตัวละครจะเพิ่มขึ้น
ทำให้ตัวละครยิงกระสุนได้ โดยที่กระสุนนี้จะสามารถทำลายระเบิดได้

เครื่องมือ
VS Code: IDE ใช้สำหรับการ Coding
Excel: ใช้สำหรับการกำหนดค่าแผนที่
Photoshop CS6: ใช้ในการตัดแต่งรูปภาพต่าง ๆ ที่นำมาใช้ในโปรแกรม

Library
java.util เป็น Package ที่มีเครื่องมือพื้นฐานเช่น ฟังก์ชั่นการสุ่มค่า รับค่าผ่านคีย์บอร์ด  เป็นต้น
java.awt เป็น  API เอาไว้ใช้สำหรับการทำ GUI หรือ windows-based application บน Java
java.io เป็น Package ที่มีระบบการรับ-ส่งข้อมูลต่าง ๆ ให้ใช้งาน
java.text เป็น Package ที่มี Class และ Interfaces สำหรับตัวอักษร วันที่ ตัวเลข และข้อความต่าง ๆ
java.net เป็น Package ที่บรรจุ Class สำหรับการเขียน Networking Application
javax.imageio บรรจุ Class สำหรับ methods ในการหา ImageReaders และ ImageWriters
javax.swing เป็น API ที่ใช้สร้าง GUI สำหรับโปรแกรม Java


Class
Folder: application
AssetSetter.java - ใช้สำหรับจัดวาง Item ลงบนแผนที่
CollisionChecker.java	- ใช้เพื่อตรวจสอบว่าตัวละครหรือวัตถุชนกับสิ่งใดหรือไม่
GamePanel.java - รับผิดชอบการวาดหน้าต่างเกมรวมถึง render ภาพต่าง ๆ ที้งยังเป็น Class กลางของ Class อื่น ๆ
KeyHandler.java - ใช้เพื่อตรวจจับการส่งข้อมูลผ่านทางแป้นพิมพ์ของผู้ใช้
Main.java - เป็น Class หลักใช้กำหนดคุณสมบัติของหน้าต่างเกมและเรียกใช้ GamePanel
UI.java - รับผิดชอบการวาด UI ของโปรแกรม
UtilityTool.java - ฟังก์ชั่นลัดสำหรับการเรียกข้อมูลรูปภาพเพื่อให้เรียกใช้งานได้สะดวกขึ้น

Folder: entity
	Entity.java - Superclass ของ Bullet และ Player มีการกำหนดค่ากลางของทั้ง 2 Class ไว้ใน Class นี้
	Bullet.java - Class สำหรับการกำหนดคุณสมบัติและค่าต่าง ๆ ของกระสุน
	Player.java - Class สำหรับการกำหนดคุณสมบัติและค่าต่าง ๆ ของตัวละคร

Folder: object
	Object_Bomb.java - Class สำหรับการกำหนดคุณสมบัติและค่าต่าง ๆ ของระเบิด
	Object_Health.java - Class สำหรับการกำหนดคุณสมบัติและค่าต่าง ๆ ของถัง ET
	Object_Heart.java - Class สำหรับการกำหนดคุณสมบัติและค่าต่าง ๆ ของหัวใจ (HP ตัวละคร)
	SuperObject.java - Superclass ของ Object Class ทั้งหมดมีการกำหนดค่ากลางของทุก Class ไว้ใน Class นี้

	Folder: clientserver
	หมายเหตุ เนื่องจากบัคที่เกิดขึ้นคือ Slot ของ Player ที่ถูกกำหนดไว้ (4 ผู้เล่น) ไม่สามารถมีการเชื่อมต่อเข้ามาซ้ำได้หากมี Client คนใดคนหนึ่งตัดการเชื่อมต่อไปแม้ว่าจะกำหนดให้ Slot นั้นว่างแล้วก็ตาม (Client สามารถเชื่อมต่อได้แต่ไม่สามารถใช้งานและรับ-ส่งข้อมูลใด ๆ ได้) และการให้ Client (คนอื่นที่ยังไม่ตัดการเชื่อมต่อ) ใช้งานต่อไปจะทำให้เกิดบัคที่ตามมาอีกหลังจากที่โปรแกรมทำงานผ่านไปได้สักพัก ส่งผลให้ให้ยังไม่สามารถนำระบบ Client-server ไปใช้กับตัวเกมได้และไม่สามารถทำให้ตัวเกมสมบูรณ์ได้
Client.java - Class สำหรับการทดสอบการเชื่อมต่อของ Client [ไม่สมบูรณ์]
Server.java - Class สำหรับการทดสอบการทำงานของ Server [ไม่สมบูรณ์]
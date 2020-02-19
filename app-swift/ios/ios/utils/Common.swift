//
//  Common.swift
//  ios
//
//  Created by Peter Zhan on 2017/10/18.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

import Foundation
import MBProgressHUD

struct Common {
   static func dateToString(_ date:Date,format:String) -> String {
        let formatter = DateFormatter()
        formatter.dateFormat = format
        return formatter.string(from: Date())
//        let yourDate = formatter.date(from: myString)
//        formatter.dateFormat = "dd-MMM-yyyy"
//        let myStringafd = formatter.string(from: yourDate!)
    }
 
    static func ShareAppDelegate () -> AppDelegate {
        return UIApplication.shared.delegate as! AppDelegate
    }
    
    static func showHUDAddedTo(view:UIView,animated:Bool) -> MBProgressHUD {
        return MBProgressHUD.showAdded(to:view,animated:animated)
    }
}

//extension NSObject {
//    func saveOrUpdateDB() -> (Bool) {
//       //let globalHelper = LKDBHelper.getUsingLKDBHelper()
//        if(self.isExistsFromDB()){
//            return self.updateToDB()
//        }else{
//            return self.saveToDB()
//        }
//    }
//    //Self!
//    static func FindByID(_ id:String) -> (Any?){
////        if(pvalue.isEmpty){
////            return nil
////        }
////
////        let infos = self.getModelInfos()
////
////        var primaryKeys = infos.primaryKeys;
////        if(primaryKeys == nil){
////            return nil
////        }
////
////        var pwhere = "";
////        if (primaryKeys!.count > 0) {
////            let pk = primaryKeys![0] as! String
////            if(!LKDBUtils.checkStringIsEmpty(pk)){
////                let property = infos.object(withSqlColumnName:pk)
////                pwhere = property!.sqlColumnName + "=" + pvalue;
////            }
////        }
////
////        if(pwhere.isEmpty)
////        {
////            return nil;
////        }
//
//        let db = LKDBHelper.getUsingLKDBHelper()
//        return db.searchSingle(self.classForCoder(), where:"id="+id, orderBy: nil)
//    }
//}

extension Int
{
    func hexedString() -> String
    {
        return NSString(format:"%02x", self) as String
    }
}

extension NSData
{
    func hexedString() -> String
    {
        var string = String()
        let unsafePointer = bytes.assumingMemoryBound(to: UInt8.self)
        for i in UnsafeBufferPointer<UInt8>(start:unsafePointer, count: length)
        {
            string += Int(i).hexedString()
        }
        return string
    }
    func MD5() -> NSData
    {
        let result = NSMutableData(length: Int(CC_MD5_DIGEST_LENGTH))!
        let unsafePointer = result.mutableBytes.assumingMemoryBound(to: UInt8.self)
        CC_MD5(bytes, CC_LONG(length), UnsafeMutablePointer<UInt8>(unsafePointer))
        return NSData(data: result as Data)
    }
}

extension String
{
    func MD5() -> String
    {
        let data = (self as NSString).data(using: String.Encoding.utf8.rawValue)! as NSData
        return data.MD5().hexedString()
    }
}



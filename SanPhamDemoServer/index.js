const express = require('express');
const mysql = require('mysql2');
const bodyParser = require('body-parser');
const app = express();

// ...
app.use(bodyParser.urlencoded({ extended: true }));

// Kết nối đến MySQL
const connection = mysql.createConnection({
    host: 'sql.freedb.tech',
    user:"freedb_db_thinh",
    password: 'tbG7xWrcXZs?HNw',
    database:'freedb_db_thinh'
});

// Middleware để sử dụng EJS làm view engine
app.set('view engine', 'ejs');
////////////////////////////////////////////////////////////////////////////////////////////////////////////SERVER
////////////////list
// Route để hiển thị dữ liệu từ truy vấn
app.get('/', (req, res) => {
  // Thực hiện truy vấn
  connection.query('SELECT * FROM Ban', (err, results, fields) => {
    if (err) {
      console.error('Lỗi truy vấn:', err);
      res.status(500).send('Lỗi truy vấn dữ liệu');
    } else {
      // Render trang HTML và truyền dữ liệu từ truy vấn
      res.render('list', { data: results });
    }
  });
});
//////////// addddd
app.get('/add', (req, res) => {
    res.render('add')
});

app.get('/test', (req, res) => {
    res.render('test')
});
// Route để xử lý yêu cầu POST từ biểu mẫu thêm mới đối tượng
app.post('/add', (req, res) => {
 
    const newObjectten = req.body.ten;
    const newObjectgiovao = req.body.giovao;
    const newObjectgia = req.body.gia;
    const newObjecttrangthai = req.body.trangthai;
    const newObjectngaythang = req.body.ngaythang;
  
    // Thực hiện truy vấn để thêm mới đối tượng vào cơ sở dữ liệu
    const sql = 'INSERT INTO Ban (ten,giovao,gia,trangthai,ngaythang) VALUES (?,?,?,?,?)';
    connection.query(sql, [newObjectten,newObjectgiovao,newObjectgia,newObjecttrangthai,newObjectngaythang], (err, results, fields) => {
      if (err) {
        console.error('Lỗi thêm mới đối tượng:', err);
        res.status(500).send('Lỗi thêm mới đối tượng');
      } else {
        console.log('Đã thêm mới đối tượng thành công!');
        // Sau khi thêm mới, chuyển hướng trở lại trang chính
        res.redirect('/');
      }
    });
  });


  ///////////// update

// Route để hiển thị trang chỉnh sửa đối tượng
app.get('/edit/:id', (req, res) => {
    const objectId = req.params.id;
  
    // Thực hiện truy vấn để lấy thông tin của đối tượng cần chỉnh sửa
    const sql = 'SELECT * FROM Ban WHERE id = ?';
    connection.query(sql, [objectId], (err, result) => {
      if (err) {
        console.error('Lỗi truy vấn:', err);
        res.status(500).send('Lỗi truy vấn dữ liệu');
      } else {
        // Render trang chỉnh sửa và truyền dữ liệu của đối tượng
        res.render('update', { data: result[0] });
      }
    });
  });
  
  // Route để xử lý yêu cầu POST từ biểu mẫu chỉnh sửa đối tượng
  app.post('/edit/:id', (req, res) => {
    const objectId = req.params.id;
    const updateObjectten = req.body.ten;
    const updateObjectgiovao = req.body.giovao;
    const updateObjectgia = req.body.gia;
    const updateObjecttrangthai = req.body.trangthai;
    const updateObjectngaythang = req.body.ngaythang;
  
  
    // Thực hiện truy vấn để cập nhật thông tin của đối tượng
    const sql = 'UPDATE Ban SET ten = ?, giovao =?, gia =?,trangthai=?,ngaythang=?  WHERE id = ?';
    connection.query(sql, [updateObjectten,updateObjectgiovao,updateObjectgia,updateObjecttrangthai,updateObjectngaythang, objectId], (err, result) => {
      if (err) {
        console.error('Lỗi cập nhật đối tượng:', err);
        res.status(500).send('Lỗi cập nhật đối tượng');
      } else {
        console.log('Đã cập nhật đối tượng thành công!');
        // Sau khi cập nhật, chuyển hướng trở lại trang chính
        res.redirect('/');
      }
    });
  });


  ////////////// delete
  // Route để xử lý yêu cầu xóa đối tượng
app.get('/delete/:id', (req, res) => {
    const objectId = req.params.id;
  
    // Thực hiện truy vấn để xóa đối tượng
    const sql = 'DELETE FRom Ban WHERE id = ?';
    connection.query(sql, [objectId], (err, result) => {
      if (err) {
        console.error('Lỗi xóa đối tượng:', err);
        res.status(500).send('Lỗi xóa đối tượng');
      } else {
        console.log('Đã xóa đối tượng thành công!');
        // Sau khi xóa, chuyển hướng trở lại trang chính
        res.redirect('/');
      }
    });
  });
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////API
  // Định nghĩa API để lấy danh sách từ MySQL
app.get('/api/getListban', (req, res) => {
  const query = 'SELECT * FROM Ban';  // Thay thế 'ten_bang' bằng tên bảng của bạn

  connection.query(query, (error, results) => {
    if (error) {
      console.error('Lỗi truy vấn MySQL: ' + error.stack);
      res.status(500).send('Lỗi server');
      return;
    }

    res.json(results);
  });
});
// Định nghĩa API để xóa đối tượng từ MySQL
app.post('/api/delitemban/:id', (req, res) => {
  const id = req.body.id;
  const query = 'DELETE FROM Ban WHERE id = ?';  // Thay thế 'ten_bang' bằng tên bảng của bạn và 'id' là trường khóa chính

  connection.query(query, id, (error, results) => {
    if (error) {
      console.error('Lỗi truy vấn MySQL: ' + error.stack);
      res.status(500).send('Lỗi server');
      return;
    }

    res.send('Xóa đối tượng thành công');
  });
});

// Định nghĩa API để cập nhật đối tượng trong MySQL
app.post('/api/updateitemban/:id', (req, res) => {
  const objectId = req.body.id;
  const updateObjectten = req.body.ten;
  const updateObjecttrangthai = req.body.trangthai;


  // Thực hiện truy vấn để cập nhật thông tin của đối tượng
  const sql = 'UPDATE Ban SET ten = ?,trangthai=?  WHERE id = ?';
  connection.query(sql, [updateObjectten,updateObjecttrangthai, objectId], (err, result) => {
    if (err) {
      console.error('Lỗi cập nhật đối tượng:', err);
      res.status(500).send('Lỗi cập nhật đối tượng');
    } else {
      console.log('Đã cập nhật đối tượng thành công!');
      res.send(' cập nhật đối tượng thành công');
      // Sau khi cập nhật, chuyển hướng trở lại trang chính
    
    }
  });
});

///// them ban 
app.post('/api/addban', (req, res) => {
 
  const newObjectten = req.body.ten;
  const newObjectgiovao = req.body.giovao;
  const newObjectgia = req.body.gia;
  const newObjecttrangthai = req.body.trangthai;
  const newObjectngaythang = req.body.ngaythang;

  // Thực hiện truy vấn để thêm mới đối tượng vào cơ sở dữ liệu
  const sql = 'INSERT INTO Ban (ten,giovao,gia,trangthai,ngaythang) VALUES (?,?,?,?,?)';
  connection.query(sql, [newObjectten,newObjectgiovao,newObjectgia,newObjecttrangthai,newObjectngaythang], (err, results, fields) => {
    if (err) {
      console.error('Lỗi thêm mới đối tượng:', err);
      res.status(500).send('Lỗi thêm mới đối tượng');
    } else {
      console.log('Đã thêm mới đối tượng thành công!');
      res.send('Cập nhật đối tượng thành công');
      // Sau khi thêm mới, chuyển hướng trở lại trang chính
    
    }
  });
});

/////////////                                                                                      bang hoa don /////////////////

// Tạo API thêm mới với ID tự tăng
app.post('/api/addhoadon', (req, res) => {
 


  // Tạo một requestId ngẫu nhiên để liên kết với yêu cầu ban đầu
  const requestId = generateRequestId();

  // Gửi thông báo xác nhận yêu cầu và requestId về client
  res.status(200).json({ message: 'Server đang chờ xác nhận yêu cầu.', requestId: requestId });
  console.log('Server đang chờ xác nhận yêu cầu.');


});


/////////////////////////// Công ty

app.post('/api/addcongty', (req, res) => {
 
 
  const newIDCT = req.body.idCongTy;
  const tenct = req.body.tencongty;
  const newdiachi = req.body.diachicongty;
  const newlinhvuc = req.body.linhvuc;
  const newgioithieu = req.body.gioithieu;


  // Thực hiện truy vấn để thêm mới đối tượng vào cơ sở dữ liệu
  const sql = 'INSERT INTO CongTy (idCongTy,tencongty,diachicongty,linhvuc,gioithieu) VALUES (?,?,?,?,?)';
  connection.query(sql, [newIDCT,tenct,newdiachi,newlinhvuc,newgioithieu], (err, results, fields) => {
    if (err) {
      console.error('Lỗi truy vấn MySQL: ' + err.stack);
      res.status(500).send('Lỗi server');
     res.send
    } else {
      res.send('Cập nhật đối tượng thành công');
      // Sau khi thêm mới, chuyển hướng trở lại trang chính
      
    }
  });
});

//////////////////////////////////////
app.get('/api/getListyeucau', (req, res) => {
  const query = 'SELECT * FROM YeuCau';  // Thay thế 'ten_bang' bằng tên bảng của bạn

  connection.query(query, (error, results) => {
    if (error) {
      console.error('Lỗi truy vấn MySQL: ' + error.stack);
      res.status(500).send('Lỗi server');
      return;
    }

    res.json(results);
  });
});
app.post('/addyeucau', (req, res) => {
  // Tạo một requestId ngẫu nhiên để liên kết với yêu cầu ban đầu
  const requestId = req.body.requestId;
  const trangthai = req.body.trangthai;

  const sql = 'INSERT INTO YeuCau (requestId,trangthai) VALUES (?,?)';
  connection.query(sql, [requestId,trangthai], (err, results, fields) => {
    if (err) {
      console.error('Lỗi truy vấn MySQL: ' + err.stack);
      res.status(500).send('Lỗi server');
    } else {
      // Sau khi thêm mới, chuyển hướng trở lại trang chính
      console.log('Cập nhật đối tượng thành công');
      // Gửi thông báo xác nhận yêu cầu và requestId về client
      res.status(200).json({ message: 'Server đang chờ xác nhận yêu cầu.', requestId: requestId });
      console.log('Server đang chờ xác nhận yêu cầu.');
    }
  });
});

app.post('/addperson', (req, res) => {
  const newPerson = req.body;
  console.log("New")

  // Gửi thông báo xác nhận yêu cầu về client để người dùng chọn
  res.status(200).json({ message: 'Server đang chờ xác nhận yêu cầu.', requestId: 'uniqueRequestId' });
});

// app.post('/confirmRequest', (req, res) => {
//   const requestId = req.body.requestId;
//   const isAccepted = req.body.isAccepted;

//   // Xử lý xác nhận từ client
//   if (isAccepted) {
//     // Thực hiện hành động khi chấp nhận yêu cầu
//     // Ví dụ: Thêm mới đối tượng vào danh sách
//     const newPerson = /* ... */

//    // people.push(newPerson);
//     res.status(200).json({ message: 'Yêu cầu đã được chấp nhận.' });

//     console.log("success")
//   } else {
//     // Thực hiện hành động khi từ chối yêu cầu
//     res.status(200).json({ message: 'Yêu cầu đã bị từ chối123.' });
//     console.log("err" )
//   }
// });
app.post('/confirmRequest', (req, res) => {
  const isAccepted = req.body.isAccepted;

  // Xử lý xác nhận từ client
  if (isAccepted) {
    const newIDUV = req.body.idUngVien;
    const newIDCT = req.body.idCongTy;
    const newngay = req.body.ngay;
    const newnoidung = req.body.noidung;
    const newkihan = req.body.kihan;
    const newtrangthai = req.body.trangthai;
    const newthanhtien = req.body.thanhtien;

    // Thực hiện truy vấn để thêm mới đối tượng vào cơ sở dữ liệu
    const sql = 'INSERT INTO HoaDon (idUngVien,idCongTy,ngay,noidung,kihan,trangthai,thanhtien) VALUES (?,?,?,?,?,?,?)';
    connection.query(sql, [newIDUV,newIDCT,newngay,newnoidung,newkihan,newtrangthai,newthanhtien], (err, results) => {
      if (err) {
        console.error('Lỗi truy vấn MySQL: ' + err.stack);
        res.status(500).json({ message: 'Lỗi server' });
      } else {
        res.status(200).json({ message: 'Yêu cầu đã được chấp nhận.' });
        console.log('Yêu cầu đã được chấp nhận.');
        // Sau khi thêm mới, chuyển hướng trở lại trang chính
      }
    });
  } else {
    res.status(200).json({ message: 'Yêu cầu đã bị từ chối.' });
    console.log('Yêu cầu đã bị từ chối.');
  }
});

app.post('/api/updateyeucau/:requestId', (req, res) => {
  const objectId = req.params.requestId;
  const updateObjecttrangthai = req.body.trangthai;


  // Thực hiện truy vấn để cập nhật thông tin của đối tượng
  const sql = 'UPDATE YeuCau set trangthai=?  WHERE requestId = ?';
  connection.query(sql, [updateObjecttrangthai, objectId], (err, result) => {
    if (err) {
      console.error('Lỗi cập nhật đối tượng:', err);
      res.status(500).send('Lỗi cập nhật đối tượng');
    } else {
      console.log('Đã cập nhật đối tượng thành công!');
      res.send(' cập nhật đối tượng thành công');
      // Sau khi cập nhật, chuyển hướng trở lại trang chính
    
    }
  });
});



function generateRequestId() {
  return Math.random().toString(36).substr(2, 9);
}

// app.post('/api/delitemyeucau/:id', (req, res) => {
//   const requestId = req.body.id;
//   const query = 'DELETE FROM YeuCau WHERE requestId =?';  // Thay thế 'ten_bang' bằng tên bảng của bạn và 'id' là trường khóa chính

//   connection.query(query, requestId, (error, results) => {
//     if (error) {
//       console.error('Lỗi truy vấn MySQL: ' + error.stack);
//       res.status(500).send('Lỗi server');
//       return;
//     }

//     res.send('Xóa đối tượng thành công');
//   });
// });
app.post('/getthongbao', (req, res)=>{

  const idUngVien = req.body.idUngVien;

const sql = `
  SELECT YeuCau.requestId, YeuCau.trangthai, YeuCau.idUngVien
  FROM YeuCau
  JOIN UngVien ON YeuCau.idUngVien = UngVien.idUngVien
  WHERE YeuCau.trangthai = 1 AND UngVien.idUngVien = ?;
`;

connection.query(sql, [idUngVien], (err, result) => {
  if (err) {
    console.error('Lỗi truy vấn:', err);
  } else {
    console.log('Dữ liệu truy vấn:', result);
  }

 
});

});
app.post('/api/delitemyeucau/:id', (req, res) => {
  const requestId = req.body.id;
  const query = 'DELETE FROM YeuCau WHERE requestId = ?';  // Thay thế 'ten_bang' bằng tên bảng của bạn và 'id' là trường khóa chính

  connection.query(query, requestId, (error, results) => {
    if (error) {
      console.error('Lỗi truy vấn MySQL: ' + error.stack);
      res.status(500).send('Lỗi server');
      return;
    }

    res.send('Xóa đối tượng thành công');
  });
});
// Khởi động server
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server đang chạy tại http://localhost:${PORT}`);
});

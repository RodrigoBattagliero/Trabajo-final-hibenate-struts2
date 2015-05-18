/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author rodrigo
 * @param <ClaseGenerica>
 */
public interface IQueries <ClaseGenerica> {
        public int create(ClaseGenerica c);
        public boolean delete(Object c);
        public boolean update(ClaseGenerica c);
        public ClaseGenerica selectOne(Object key);
        public List<ClaseGenerica> selectAll();
        public List<ClaseGenerica> selectRelated(Object key);
}

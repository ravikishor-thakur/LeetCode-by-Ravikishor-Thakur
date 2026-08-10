class Solution:
    def getImportance(self, employees: List['Employee'], id: int) -> int:
        mp = {e.id: e for e in employees}

        def dfs(emp_id):
            emp = mp[emp_id]
            total = emp.importance
            for sub in emp.subordinates:
                total += dfs(sub)
            return total

        return dfs(id)